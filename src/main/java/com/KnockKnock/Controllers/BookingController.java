package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Services.BookingService;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping("/addBooking/{id}")
    public String addBooking(@RequestBody Booking booking,@PathVariable Long id){
        System.out.println("I am booking service for customer id:" + id);

        Date date = new Date();
        Customer customer = customerService.findById(id);
        Booking book = new Booking(date,booking.getBookingServStartDate(),booking.getBookingServEndDate(),booking.getBookingComments(),customer,booking.getProfessionalServices(),"p");

        bookingService.save(book);

        return "{\"status\":true, \"Booking\":{\"bookingComments\":\"" + booking.getBookingComments() +"\"}}";
    }

    @GetMapping("/getBookings/{id}")
    public ResponseEntity<String> getBookings(@PathVariable Long id) {

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAll());
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findAllByCustomerCustomerId(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPendingBookings/{id}")
    public ResponseEntity<String> getCustomerPendingBookings(@PathVariable Long id) {

        List<String> l = new ArrayList<>();
        l.add("p");
        l.add("a");
        l.add("r");

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAll());
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);


            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findAllByCustomerCustomerIdAndBookingStatusIn(id,l)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCompletedBookings/{id}")
    public ResponseEntity<String> getCustomerCompletedBookings(@PathVariable Long id) {

        List<String> l = new ArrayList<>();
        l.add("c");
        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAll());
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findAllByCustomerCustomerIdAndBookingStatusIn(id,l)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookings")
    public ResponseEntity<String> getBookings() {

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAll());
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(bookingService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
