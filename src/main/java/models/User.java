package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Transient private List<House> userHouses;
    @Transient private List<House> userBookings;

    public User() { }

    @Override
    public String toString() {
        return "\nUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userHouses=" + userHouses +
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

    public List<House> getUserHouses() {
        return userHouses;
    }

    public void setUserHouses(List<House> userHouses) { this.userHouses = userHouses; }

    public List<House> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<House> userBookings) {
        this.userBookings = userBookings;
    }
}
