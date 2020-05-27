package com.KnockKnock.Entities;

import java.util.Date;

public class Customer_Booking {

    private Long bookingId;

    private Date bookingDate;

    private String bookingComments;

    private String customerName;

    public Customer_Booking(Long bookingId, Date bookingDate, String bookingComments, String customerName) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingComments = bookingComments;
        this.customerName = customerName;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingComments() {
        return bookingComments;
    }

    public void setBookingComments(String bookingComments) {
        this.bookingComments = bookingComments;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
