package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Document
public class House {
    @Id private String id;
    private String title;
    private String address;
    private String city;
    private String propertyType;
    private String Description;
    private ArrayList<String> images;
    private int price;
    
    public House(){}
    
    public House(String title, String address, String city, String propertyType, String description, ArrayList<String> images, int price) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.propertyType = propertyType;
        Description = description;
        this.images = images;
        this.price = price;
    }
    
//    Lägga alla bilder i en url variabel och sedan dom i en lista? eller bara ha en bild tillsvidare.
    
    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", Description='" + Description + '\'' +
                ", images=" + images +
                ", price=" + price +
                '}';
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String description) {
        Description = description;
    }
    
    public ArrayList<String> getImages() {
        return images;
    }
    
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
}
