package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.util.Date;
import java.util.List;

@Document
public class Booking {
    @Id
    private String id;
    private String bookerId;
    private String houseId;
    private List<Date> chosenDate;

    public Booking() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookerId() {
        return bookerId;
    }

    public void setBookerId(String bookerId) {
        this.bookerId = bookerId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public List<Date> getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(List<Date> chosenDate) {
        this.chosenDate = chosenDate;
    }
}

