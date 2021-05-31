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

        app.get("/rest/cheapest-houses", (req, res) -> {
            res.json(collection("House").find(op -> {
                op.sort = "price=asc";
                op.limit = 10;
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

                if (grades.size() != 0) {
                    for (int i = 0; i < grades.size(); i++) {
                        totalScore = totalScore + grades.get(i);
                    }
                    average = (double) totalScore / (double) grades.size();
                    housesUnsorted.put(house.getId(), average);
                }
                if (housesUnsorted.size() == 5) return;
            });

            ArrayList<Double> averageGrades = new ArrayList<>(housesUnsorted.values());
            Collections.sort(averageGrades, Collections.reverseOrder());
            Set<String> keys = housesUnsorted.keySet();
            List<String> houseList = new ArrayList<>();

            averageGrades.forEach(value -> {
                for (String key : keys) {
                    if (housesUnsorted.get(key).equals(value)) {
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
    
        app.put("/rest/users/:id", (req, res) -> {
            User user = collection("User").findById(req.params("id"));
            User updates = req.body(User.class);
            user.setFirstName(updates.getFirstName());
            user.setLastName(updates.getLastName());
            user.setFullName(updates.getFirstName() + " " + updates.getLastName());
            user.setDescription(updates.getDescription());
            user.setGender(updates.getGender());
            user.setImage(updates.getImage());
            collection("User").save(user);
            String test = "Svar från Backend";
            res.json(user);
        });

        app.get("/rest/bookings", (req, res) -> {
            res.json(collection("Booking").find());
        });

        app.get("/rest/house/bookings/:id", (req, res) -> {
            List<Booking> bokningar = collection("Booking").find("houseId==" + req.params("id"));
            res.json(bokningar);
        });

        app.post("/rest/bookings", (req, res) -> {
            Booking booking = req.body(Booking.class);
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
            collection("Review").save(review);
        });

        app.post("/rest/post-reply", (req, res) -> {
            Reply reply = req.body(Reply.class);
            collection("Reply").save(reply);
        });

        app.get("/rest/reviews/:id", (req, res) -> {
            res.json(collection("Review").find("gradedHouse==" + req.params("id")));
        });

        app.get("/rest/replies/:id", (req, res) -> {
            res.json(collection("Reply").find("reviewId==" + req.params("id")));
        });

        app.delete("rest/bookings/:id", (req, res) -> {
            res.json(collection("Booking").deleteById(req.params("id")));
        });

        app.post("/api/uploads/", (req, res) -> {
            List<String> uploadNames = new ArrayList<>();
            List<UploadedFile> files = req.formDataFiles("files");

            for (UploadedFile file : files) {
                String filename = "/images/houses/" + file.getFilename();
                uploadNames.add(filename);
                FileUtil.streamToFile(file.getContent(), "Frontend/public" + filename);
            }
            res.json(uploadNames);
        });

        // start server
        app.listen(4000);
    }
}