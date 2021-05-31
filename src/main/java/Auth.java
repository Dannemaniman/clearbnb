import express.Express;

import models.User;
import utilities.HashPassword;

import java.util.Map;

import static nosqlite.Database.collection;

public class Auth {

    private Express app;

    public Auth(Express app) {
        this.app = app;
        initAuth();
    }

    private void initAuth() {
        app.post("/api/register", (req, res) -> {
           User user = req.body(User.class);
           User exists = collection("User").findOne("email==" + user.getEmail());
           if (exists != null) {
               res.json(Map.of("Error", "User already exists"));
               return;
           }

           String hashedPassword = HashPassword.hash(user.getUserPassword());
           user.setUserPassword(hashedPassword);
           collection("User").save(user);

           req.session("current-user", user);
           res.json(user);
        });

        app.post("/api/login", (req, res) -> {
           User user = req.body(User.class);
           User userInColl = collection("User").findOne("email==" + user.getEmail());

           if (userInColl == null) {
               res.json(Map.of("Error", "Bad credentials"));
               return;
           }

           if (HashPassword.match(user.getUserPassword(), userInColl.getUserPassword())) {
               req.session("current-user", userInColl);
               res.json(userInColl);
           } else {
               res.json(Map.of("Error", "Bad credentials"));
           }
        });

        app.get("/api/whoami", (req, res) -> {
            res.json(req.session("current-user"));
        });

        app.get("/api/logout", (req, res) -> {
            req.session("current-user", null);
            res.json(Map.of("Ok", "Logged out"));
        });
    }
}
