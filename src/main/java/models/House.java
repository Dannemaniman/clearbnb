package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import nosqlite.annotations.Transient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Document
public class House {

    @Id
    private String id;
    private ArrayList<String> images;
    private String title;
    private String address;
    private String zipcode;
    private String city;
    private String propertyType;
    private Accommodation accommodation;
    private String description;
    private int price;
    private int childDiscount;
    private int seniorDiscount;
    private List<String> amenities;
    private String ownerId;
    private double[] position;
    private Date leaseStart;
    private Date leaseEnd;
    @Transient
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
                ", accommodation=" + accommodation +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", amenities=" + amenities +
                ", ownerId='" + ownerId + '\'' +
                ", position=" + Arrays.toString(position) +
                ", bookings=" + bookings +
                ", leaseStart=" + leaseStart +
                ", leaseEnd=" + leaseEnd +
                ", bookings=" + bookings +
                '}';
    }

    public int getSeniorDiscount() {
        return seniorDiscount;
    }

    public void setSeniorDiscount(int seniorDiscount) {
        this.seniorDiscount = seniorDiscount;
    }

    public int getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(int childDiscount) {
        this.childDiscount = childDiscount;
    }
    
    public double[] getPosition() {
        return position;
    }
    
    public void setPosition(double[] position) {
        this.position = position;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Date getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(Date leaseStart) {
        this.leaseStart = leaseStart;
    }

    public Date getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(Date leaseEnd) {
        this.leaseEnd = leaseEnd;
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

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
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
