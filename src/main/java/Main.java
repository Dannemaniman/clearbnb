import express.Express;
import models.House;
import models.User;

import java.util.List;

import static nosqlite.Database.collection;

/*
    C - Create  POST
    R - Read    GET
    U - Update  PUT
    D - Delete  DELETE
 */

public class Main {
  public static void main(String[] args) {
    Express app = new Express();
  
    collection(config -> {
      config.useBrowser = true;
    });
      new Auth(app);
    /*ArrayList<String> images = new ArrayList<>();
    images.add("public\\images\\House8.jpg");
  
  
    House home = new House("Hemliga huset", "Flexibel", "Sjöbo", "Tält", "Trevligt tält bla bla", images, 500);
    
    collection("House").save(home);*/
  
    app.get("/rest/houses", (req, res) -> {
      List<House> houses = collection("House").find();
    
      res.json(houses);
    });

    app.post("rest/users", (req, res)-> {
      User user = req.body(User.class);

      collection("User").save(user);
      res.json(user);
    });
    
    // start server
    app.listen(4000);
  }
}
