import express.Express;
import models.Booking;
import models.Review;
import models.Review;

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

    new Auth(app);

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

    // start server
    app.listen(4000);
  }
}
