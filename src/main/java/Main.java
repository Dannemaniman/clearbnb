import express.Express;
import models.House;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  
    ArrayList<String> images = new ArrayList<>();
    images.add("Frontend/public/images/House1.jpg");
  
  
    House home = new House("Casa Mia", "Hemmavägen 13", "Sjöbo", "Slott", "Ett jävla nice hem", images, 2000);
    
    collection("House").save(home);
  
    app.get("/rest/houses", (req, res) -> {
      List<House> houses = collection("House").find();
    
      res.json(houses);
    });
    
    // start server
    app.listen(4000);
  }
}
