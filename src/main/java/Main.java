import express.Express;
import io.javalin.core.util.FileUtil;
import io.javalin.http.UploadedFile;
import models.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;
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
<<<<<<< HEAD
    
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
        List<House> houses = collection("House").find();
        TreeMap<String, Double> housesUnsorted = new TreeMap<>();

        houses.forEach(house -> {
            List<Review> test = collection("Review").find("gradedHouse==" + house.getId());
            ArrayList<Integer> grades = new ArrayList<>();
            int totalScore = 0;
            double average;

            test.forEach(review -> {
                grades.add(review.getGrade());
            });

            if(grades.size() != 0) {
                for(int i = 0; i < grades.size(); i++) {
                    totalScore = totalScore + grades.get(i);
                }
                average = (double)totalScore / (double)grades.size();
                housesUnsorted.put(house.getId(), average);
            }
            if(housesUnsorted.size() == 5) return;
        });

        ArrayList<Double> averageGrades = new ArrayList<>(housesUnsorted.values());
        Collections.sort(averageGrades, Collections.reverseOrder());
        Set<String> keys = housesUnsorted.keySet();
        List<String> houseList = new ArrayList<>();

        averageGrades.forEach( value -> {
            for( String key : keys ){
                if( housesUnsorted.get(key).equals(value)){
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

    app.post("/api/uploads/", (req, res) -> {
        List<String> uploadNames = new ArrayList<>();
        
        List<UploadedFile> files = req.formDataFiles("files");
        

        for (UploadedFile file : files) {
            //String id = req.params("id");
            String filename = "/images/houses/" + file.getFilename();
            uploadNames.add(filename);
            // save file to static directory (creates dirs if necessary)
            FileUtil.streamToFile(file.getContent(), "Frontend/public" + filename);
        }
        
        res.json(uploadNames);
    });

    // start server
    app.listen(4000);
  }
=======
        
        
        
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
>>>>>>> acbd8397f881500fbc15418c58e80dbfb9854cb8
}
