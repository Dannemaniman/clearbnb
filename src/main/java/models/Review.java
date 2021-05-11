package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.awt.*;

@Document
public class Review {
   @Id
    private String id;
    private String avatar;
    private String author;
    private String review;
    private int grade;
    private String gradedHouse;
    
    public Review(){};
    
    @Override
    public String
    toString() {
        return "Reviews{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", grade=" + grade +
                ", gradedHouse='" + gradedHouse + '\'' +
                '}';
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public String getGradedHouse() {
        return gradedHouse;
    }
    
    public void setGradedHouse(String gradedHouse) {
        this.gradedHouse = gradedHouse;
    }
}
