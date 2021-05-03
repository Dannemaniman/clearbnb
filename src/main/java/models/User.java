package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.util.ArrayList;

@Document
public class User {

    @Id
    private String id;
    private String userName;
    private String userPassword;
    private String email;
    private String fullName;
    private ArrayList<Home> userHomes;
    private ArrayList<Home> userBookings;

    public User() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<Home> getUserHomes() {
        return userHomes;
    }

    public void setUserHomes(ArrayList<Home> userHomes) {
        this.userHomes = userHomes;
    }

    public ArrayList<Home> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(ArrayList<Home> userBookings) {
        this.userBookings = userBookings;
    }
}
