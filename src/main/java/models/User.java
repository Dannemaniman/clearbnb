package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import nosqlite.annotations.Transient;

import java.util.List;

@Document
@JsonIgnoreProperties (ignoreUnknown = true)
public class User {
    @Id
    private String id;
    private String firstName;
    private String email;
    private String password;

    //ignore this property when saving to the collection
    @Transient
    private List<House> houses;

    //default constructor


    @Override
    public String toString() {
        return "\nUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", houses=" + houses +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
