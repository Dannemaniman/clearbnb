package models;

import express.Express;

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


            //hash password (encrypt password)

            collection("User").save(user);

            res.json(user);
        });
        //login user
        app.post("api/login", (req ,res) ->{
            User user = req.body(User.class);

        });



        //who am i? get logged in user
    }
}
