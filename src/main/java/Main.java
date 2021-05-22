import express.Express;
import models.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    review3.setGradedHouse("spYROoShRiR-KDqU1piUK");
    collection("Review").save(review3);

    User user1 = collection("User").findOne("id==" + "vYdyudpjkIFH0ri1Y4Hh7");
    Review review4 =  new Review();
    review4.setAvatar("https://robohash.org/autdelectusest.png?size=50x50&set=set1");
    review4.setAuthorId("vYdyudpjkIFH0ri1Y4Hh7");
    review4.setAuthorName(user1.getFullName());
    review4.setReview("njanjanja.!!!");
    review4.setGrade(3);
    review4.setGradedHouse("spYROoShRiR-KDqU1piUK");
    collection("Review").save(review4);

      User user2 = collection("User").findOne("id==" + "vYdyudpjkIFH0ri1Y4Hh7");
      Review review5 =  new Review();
      review5.setAvatar("https://robohash.org/autdelectusest.png?size=50x50&set=set1");
      review5.setAuthorId("vYdyudpjkIFH0ri1Y4Hh7");
      review5.setAuthorName(user2.getFullName());
      review5.setReview("njanjanja.!!!");
      review5.setGrade(5);
      review5.setGradedHouse("spYROoShRiR-KDqU1piUK");
      collection("Review").save(review5);

      User user4 = collection("User").findOne("id==" + "vYdyudpjkIFH0ri1Y4Hh7");
      Review review6 =  new Review();
      review6.setAvatar("https://robohash.org/autdelectusest.png?size=50x50&set=set1");
      review6.setAuthorId("vYdyudpjkIFH0ri1Y4Hh7");
      review6.setAuthorName(user4.getFullName());
      review6.setReview("njanjanja.!!!");
      review6.setGrade(0);
      review6.setGradedHouse("spYROoShRiR-KDqU1piUK");
      collection("Review").save(review6);
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

        //1. Push houseIds to new Array
        ArrayList<String> houseIds = new ArrayList<>();
        List<House> houses = collection("House").find();
        houses.forEach(item -> {
            houseIds.add(item.getId());
        });
        //2. Go through all Reviews and push

        TreeMap<String, Double> housesUnsorted = new TreeMap<>();

        houseIds.forEach(houseId -> {
            //do this for every houseId in order to find out every houses score
            List<Review> test = collection("Review").find("gradedHouse==" + houseId);
            ArrayList<Integer> grade = new ArrayList<>();
            int totalScore = 0;
            double average = 0;

            //get grades and push to array
            test.forEach(review -> {
                grade.add(review.getGrade());
            });

            for(int i = 0; i < grade.size(); i++) {
                totalScore = totalScore + grade.get(i);
            }

            if(grade.size() != 0) {
                average = (double)totalScore / (double)grade.size();
                housesUnsorted.put(houseId, average);
            }

            if(housesUnsorted.size() == 5) return;
        });

        ArrayList<Double> gradez = new ArrayList<>(housesUnsorted.values());
        Collections.sort(gradez, Collections.reverseOrder());

        //get all the TreeMap keys
        Set<String> keys = housesUnsorted.keySet();

        //value for which we need to fetch key
        List<String> houseList = new ArrayList<>();
            gradez.forEach( value -> {
                for( String key : keys ){
                    if( housesUnsorted.get(key).equals(value)){
                        System.out.println("Key for value " + value + " is: " + key);
                        houseList.add(key);
                        return;
                    }
                }
        });
        res.json(collection("House").find("id==" + houseList));
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
