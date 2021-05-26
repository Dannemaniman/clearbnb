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
    private String firstName;
    private String lastName;
    private String fullName = firstName + lastName;
    private String description;
    private String gender;
    private String image;
    // ignore this property when saving to the collection
    @Transient private List<House> userHouses;
    @Transient private List<House> userBookings;

    public User() { }
    
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", userHouses=" + userHouses +
                ", userBookings=" + userBookings +
                '}';
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
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
