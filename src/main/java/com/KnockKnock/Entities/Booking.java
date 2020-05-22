package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonFilter("customerBookingOnly")
@Entity
@Table(name = "Booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column
    @NotNull
    private Date bookingDate;

    @Column
    private Date bookingServStartDate;

    @Column
    private Date bookingServEndDate;

    @Column
    private String bookingComments;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToMany(targetEntity = ProfessionalService.class)
    private Set<ProfessionalService> professionalServices = new HashSet<>();

    @NotNull
    @Column
    private String bookingStatus;


    public Booking(@NotNull Date bookingDate, Date bookingServStartDate, Date bookingServEndDate, String bookingComments, @NotNull Customer customer, Set<ProfessionalService> professionalServices, @NotNull String bookingStatus) {
        this.bookingDate = bookingDate;
        this.bookingServStartDate = bookingServStartDate;
        this.bookingServEndDate = bookingServEndDate;
        this.bookingComments = bookingComments;
        this.customer = customer;
        this.professionalServices = professionalServices;
        this.bookingStatus = bookingStatus;
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

    public Date getBookingServStartDate() {
        return bookingServStartDate;
    }

    public void setBookingServStartDate(Date bookingServStartDate) {
        this.bookingServStartDate = bookingServStartDate;
    }

    public Date getBookingServEndDate() {
        return bookingServEndDate;
    }

    public void setBookingServEndDate(Date bookingServEndDate) {
        this.bookingServEndDate = bookingServEndDate;
    }

    public String getBookingComments() {
        return bookingComments;
    }

    public void setBookingComments(String bookingComments) {
        this.bookingComments = bookingComments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
