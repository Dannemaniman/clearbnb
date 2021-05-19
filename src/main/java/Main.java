import express.Express;
import models.Booking;
import models.House;
import nosqlite.Collection;

import java.util.Collections;

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


    app.get("/rest/houses", (req, res) -> {
        res.json(collection("House").find());
    });

    app.get("/rest/besthouses", (req, res) -> {

        collection("house").find("reviews==");

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
    
    app.get("/rest/reviews", (req, res) -> {
        res.json(collection("Review").find());
    });


    app.get("/rest/bookings", (req, res) -> {
        res.json(collection("Booking").find());
    });

    app.post("/rest/bookings", (req, res) -> {
        Booking booking = req.body(Booking.class);
        // Booking occupied = collection("Booking").find(and(eq("houseId" + booking.getHouseId()), eq("bookedDates" + booking.getBookedDates())));
        collection("Booking").save(booking);
        res.json(booking);
    });

      app.post("/rest/houses", (req, res) -> {
          System.out.println("Hejsan");
          //System.out.println(req.body());
          House house = req.body(House.class);
          //System.out.println(house);
          collection("House").save(house);
          res.json(house);
      });
      
      // start server
    app.listen(4000);
  }
}
