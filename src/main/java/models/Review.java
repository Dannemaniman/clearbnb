package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.awt.*;
import java.util.List;

@Document
public class Review {

    @Id
    private String id;
    private String avatar;
    private String author;
    private String review;
    private int grade;
    private String gradedHouse;
    private List<Reply> replies;
    
    public Review(){};

    @Override
    public String toString() {
        return  '\n' + "Review{" + '\n' +
                ", id='" + id + '\n' +
                ", avatar='" + avatar + '\n' +
                ", author='" + author + '\n' +
                ", review='" + review + '\n' +
                ", grade=" + grade + '\n' +
                ", gradedHouse='" + gradedHouse + '\n' +
                ", replies=" + replies + '\n' +
                '}';
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
