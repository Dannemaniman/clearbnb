import express.Express;
import models.Booking;
import models.House;
import models.Review;
import nosqlite.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        config.useWatcher = true;
    });


    new Auth(app);




    app.get("/rest/houses", (req, res) -> {
        res.json(collection("House").find());
    });

    app.get("/rest/best-houses", (req, res) -> {

        List<Review> reviews = collection("Review").find(op -> {
            op.sort = "grade=desc";
            op.limit = 5;
        });
        
        List<House> houses = new ArrayList<>();

        reviews.forEach(review -> {
            houses.add(collection("House").findOne("id==" + review.getGradedHouse()));
            System.out.println(review);
        });

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

    app.get("/rest/bookings/:id", (req, res) -> {
        res.json(collection("Booking").findById(req.params("id")));
    });

    app.post("/rest/bookings", (req, res) -> {
        Booking booking = req.body(Booking.class);
        // Booking occupied = collection("Booking").find(and(eq("houseId" + booking.getHouseId()), eq("bookedDates" + booking.getBookedDates())));
        collection("Booking").save(booking);
        res.json(booking);
    });

      app.post("/rest/houses", (req, res) -> {
          House house = req.body(House.class);
          collection("House").save(house);
          res.json(house);
      });

      app.post("/rest/post-review", (req, res) -> {
         Review review = req.body(Review.class);
          System.out.println(review);
          collection("Review").save(review);
      });
      
      // start server
    app.delete("rest/bookings/:id", (req, res) -> {
        res.json(collection("Booking").deleteById(req.params("id")));
    });

    // start server
    app.listen(4000);
  }
}
