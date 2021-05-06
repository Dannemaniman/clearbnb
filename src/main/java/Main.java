import express.Express;
import java.util.ArrayList;
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
  
    /*ArrayList<String> images = new ArrayList<>();
    images.add("public\\images\\House8.jpg");
  
  
    House home = new House("Hemliga huset", "Flexibel", "Sjöbo", "Tält", "Trevligt tält bla bla", images, 500);
    
    collection("House").save(home);*/
  
//    app.get("/rest/houses", (req, res) -> {
//      List<Home> houses = collection("Home").find();
//
//      res.json(houses);
//    });

    app.get("/rest/houses", (req, res) -> {
        res.json(collection("House").find());
    });

    app.get("/rest/houses/:id", (req, res) -> {
        res.json(collection("House").findById(req.params("id")));
    });

    app.get("/rest/users", (req, res) -> {
        res.json(collection("User").find());
    });

    app.get("/rest/users/:id", (req, res) -> {
        res.json(collection("User").findById(req.params("id")));
    });

    // start server
    app.listen(4000);
  }
}
