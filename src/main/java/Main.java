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
    });


    new Auth(app);


    Review review3 =  new Review();
    review3.setAvatar("https://robohash.org/autdelectusest.png?size=50x50&set=set1");
    review3.setAuthor("Hf7pJLnRbu1uExXWaXRW2");
    review3.setReview("Huset sög.   ");
    review3.setGrade(0);
    review3.setGradedHouse("o0U_oWPgWfmyFmeWjnyWk");


    collection("Review").save(review3);

    System.out.println(collection("Review").find());

    
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
    
    app.get("/rest/reviews/:id", (req, res) -> {
        //res.json(collection("Review").find());
        List<Review> review = collection("Review").findById(req.params("id"));
        List<Review> review2 = collection("Review").find();
        System.out.println(req.params("id"));
        System.out.println(review2.toString());
       // System.out.println(review);
        res.json(collection("Review").findById(req.params("id")));
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
          House house = req.body(House.class);
          collection("House").save(house);
          res.json(house);
      });

      app.post("/rest/post-review", (req, res) -> {
         Review review = req.body(Review.class);
          System.out.println(review);
          collection("Review").save(review);
      });

      app.post("/rest/post-reply", (req, res) -> {
          Review review = req.body(Review.class);
          System.out.println(review);

          //hitta rätt ställe att stoppa in reviewn i!

         // collection("Review").save(review);
      });
      
      // start server
    app.listen(4000);
  }
}
