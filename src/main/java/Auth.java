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
    private void initAuth(){
        //register user
        app.post("/api/register", (req, res) ->{
            User user = req.body(User.class);

            //check if email is not taken
            User exists = collection("User").findOne("email" + user.getEmail());

            if (exists != null){
                res.json(Map.of("error","user already have a registered"));
                return;
            }

            //hash password (encrypt password)
            String hashPassword = HashPassword.hash(user.getPassword());
            user.setPassword(hashPassword);

            collection("User").save(user);

            res.json(user);
        });
        //login user
        app.post("api/login", (req ,res) ->{
            User user = req.body(User.class);
            User userInColl = collection("User").findOne("email" + user.getEmail());

            if (userInColl == null){
                res.json(Map.of("error","Bad credential"));
                return;
            }
            if (HashPassword.match(user.getPassword(), userInColl.getPassword())){
                req.session("current-user", userInColl);
                res.json(userInColl);
            }else {
                res.json(Map.of("error", "Bad credentials"));
            }
        });

        //who am i? get logged in user
        app.get("/api/whoami", (req, res)->{
            res.json(req.session("current-user"));
        });
        app.get("/api/logout", (req, res)->{
            req.session("current-user", null);
            res.json(Map.of("ok","logged out"));
        });
    }
}
