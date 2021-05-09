package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import java.util.ArrayList;
import java.util.List;

@Document
public class House {

    @Id
    private String id;
    private ArrayList<String> images;
    private String title;
    private String address;
    private String city;
    private String propertyType;
    private String beds;
    private String description;
    private String price;
    private List<String> amenities;
    private String reviews;
    private String ownerId;
    private List<Booking> bookings;

    public House() { }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", images=" + images +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", beds=" + beds +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", amenities=" + amenities +
                ", reviews=" + reviews +
                ", ownerId='" + ownerId + '\'' +
                ", bookings=" + bookings +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAccommodation() {
        return beds;
    }

    public void setAccommodation(String beds) {
        this.beds = beds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
