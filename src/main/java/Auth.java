import express.Express;
import models.User;
import utilities.HashPassword;

import java.util.Map;

import static nosqlite.Database.collection;

/*
    /rest - is ONLY for manipulating raw entities
    /api - is used when the route handler need to perform logic
 */

public class Auth {
    private Express app;

    public Auth(Express app) {
        this.app = app;
        initAuth();
    }

    private void initAuth() {
        // register user
        app.post("/api/register", (req, res) -> {
            User user = req.body(User.class);

            // check if email is not taken
            User exists = collection("User").findOne("email==" + user.getEmail());

            // email is taken
            if(exists != null) {
                res.json(Map.of("error", "User already exists"));
                return;
            }

            // hash password (encrypt password)
            String hashedPassword = HashPassword.hash(user.getPassword()); //
            user.setPassword(hashedPassword);

            collection("User").save(user);

            // log in user
            req.session("current-user", user);

            res.json(user);
        });

        // login user
        app.post("/api/login", (req, res) -> {
            User user = req.body(User.class);

            User userInColl = collection("User").findOne("email==" + user.getEmail());

            if(userInColl == null) {
                res.json(Map.of("error", "Bad credentials"));
                return;
            }

            // validate password
            if(HashPassword.match(user.getPassword(), userInColl.getPassword())) {
                // save user in session, to remember logged in state
                req.session("current-user", userInColl);

                res.json(userInColl);
            } else {
                res.json(Map.of("error", "Bad credentials"));
            }
        });

        // who am i? get logged in user
        app.get("/api/whoami", (req, res) -> {
            // return user saved in session
            res.json(req.session("current-user"));
        });

        // logout user
        app.get("/api/logout", (req, res) -> {
            // remove user from session
            req.session("current-user", null);

            res.json(Map.of("ok", "logged out"));
        });
    }

}
