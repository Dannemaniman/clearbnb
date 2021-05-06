package models;

import java.util.Date;
import java.util.List;

public class Booking {
    private String bookerId;
    private List<Date> bookedDate;

    public Booking() { }

    public String getBookerId() {
        return bookerId;
    }

    public void setBookerId(String bookerId) {
        this.bookerId = bookerId;
    }

    public List<Date> getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(List<Date> bookedDate) {
        this.bookedDate = bookedDate;
    }
}
