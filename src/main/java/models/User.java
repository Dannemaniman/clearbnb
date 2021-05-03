package models;
import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import nosqlite.annotations.Transient;
import java.util.List;

@Document
public class User {

    @Id private String id;
    private String userName;
    private String userPassword;
    private String email;
    private String fullName;
    // ignore this property when saving to the collection
    @Transient private List<Home> userHomes;
    @Transient private List<Home> userBookings;

    public User() { }

    @Override
    public String toString() {
        return "\nUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userHomes=" + userHomes +
                ", userBookings=" + userBookings +
                '}';
    }

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

    public List<Home> getUserHomes() {
        return userHomes;
    }

    public void setUserHomes(List<Home> userHomes) {
        this.userHomes = userHomes;
    }

    public List<Home> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<Home> userBookings) {
        this.userBookings = userBookings;
    }
}
