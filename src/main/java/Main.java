import express.Express;
import models.*;
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

/*
    User user = collection("User").findOne("id==" + "vYdyudpjkIFH0ri1Y4Hh7");
    Review review3 =  new Review();
    review3.setAvatar("https://robohash.org/autdelectusest.png?size=50x50&set=set1");
    review3.setAuthorId("vYdyudpjkIFH0ri1Y4Hh7");
    review3.setAuthorName(user.getFullName());
    review3.setReview("njanjanja.!!!");
    review3.setGrade(2);
    review3.setGradedHouse("o0U_oWPgWfmyFmeWjnyWk");


    collection("Review").save(review3);

    System.out.println(collection("Review").find());
*/


    
    app.get("/rest/houses", (req, res) -> {
        res.json(collection("House").find());
    });

    app.get("/rest/cheapest-houses", (req, res) -> {
       res.json(collection("House").find(op -> {
           op.sort = "price=asc";
           op.limit = 5;
       }));
    });

    app.get("/rest/best-houses", (req, res) -> {

        // System.out.println(collection("Review").find());
   /*     List<Review> reviews = collection("Review").find(op -> {
            op.sort = "grade=desc";
            op.limit = 5;
        });
        */


        //1. Push houseIds to new Array
        ArrayList<String> houseIds = new ArrayList<>();
        List<House> houses = collection("House").find();
        houses.forEach(item -> {
            houseIds.add(item.getId());
        });

        System.out.println(houseIds);

        //2. Go through all Reviews and push
        List<Review> reviews = collection("Review").find();
        houseIds.forEach(item -> {
            collection("Review").find(item);
        });



/*
        List<House> houses = new ArrayList<>();

        reviews.forEach(review -> {
            houses.add(collection("House").findOne("id==" + review.getGradedHouse()));
           // System.out.println(review);
        }); */
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

      app.post("/rest/post-reply", (req, res) -> {
          Reply reply = req.body(Reply.class);
          collection("Reply").save(reply);
      });

      app.get("/rest/reviews/:id", (req, res) -> {
          //   List<Review> review = collection("Review").find("gradedHouse==" + req.params("id"));
          res.json(collection("Review").find("gradedHouse==" + req.params("id")));
      });

      app.get("/rest/replies/:id", (req, res) -> {
          List<Reply> replies = collection("Reply").find("reviewId==" + req.params("id"));
          System.out.println(replies);
          res.json(collection("Reply").find("reviewId==" + req.params("id")));
      });
      
      // start server
    app.delete("rest/bookings/:id", (req, res) -> {
        res.json(collection("Booking").deleteById(req.params("id")));
    });

    // start server
    app.listen(4000);
  }
}
