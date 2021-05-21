package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

@Document
public class Reply {

    @Id
    private String id;
    private String ReviewId;
    private String avatar;
    private String author;
    private String reply;

    public Reply(){};

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", author='" + author + '\'' +
                ", reply='" + reply + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewId() {
        return ReviewId;
    }

    public void setReviewId(String reviewId) {
        ReviewId = reviewId;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}
