package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Services.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.print.Book;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/addBooking")
    public Booking addBooking(@RequestBody Booking booking){
        System.out.println("I am booking service for customer id:" );
         return bookingService.save(booking);
    }

    @GetMapping("/getBooking/{id}")
    public ResponseEntity<String> getCustomerBooking(@PathVariable Long id) {

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("customerBookingOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName", "bookingDate", "bookingComments",
                            "bookingServStartDate", "bookingServEndDate", "bookingStatus"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findByCustomerCustomerId(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookings")
    public ResponseEntity<String> getBookings() {

        try{
            ObjectMapper om = new ObjectMapper();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
