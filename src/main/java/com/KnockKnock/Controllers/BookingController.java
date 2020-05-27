package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Customer_Booking;
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
                    .body(om.writeValueAsString(bookingService.findAllByCustomerCustomerIdAndBookingStatus(id,"p")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCompletedBookings/{id}")
    public ResponseEntity<String> getCustomerCompletedBookings(@PathVariable Long id) {

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
                    .body(om.writeValueAsString(bookingService.findAllByCustomerCustomerIdAndBookingStatus(id,"c")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookings")
    public ResponseEntity<String> getBookings() {

        try {
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
        @GetMapping("/getPending/{id}")
         public ResponseEntity<List<Customer_Booking>> getPending(@PathVariable("id") Long id)
        {
           //Professional professional=professionalService.findById(id);
           List<Booking> booking=bookingService.findByProfessionalId(id);

           List<Customer_Booking> customers=new ArrayList<>();

           for(Booking b: booking)
           {
               Customer cus=b.getCustomer();
               Customer_Booking cb=new Customer_Booking(b.getBookingId(),b.getBookingDate(),b.getBookingComments(),cus.getCustomerName());
               customers.add(cb);
           }
           return new ResponseEntity<List<Customer_Booking>>(customers,HttpStatus.OK);

        }

        @PostMapping("/changestatus/{id}/{s}")
    public void changestatus(@PathVariable("id") Long id,@PathVariable("s") Long s)
        {
           // Long id=b.getBookingId();
            System.out.println(id+"..............................");
            Booking booking=bookingService.findById(id);
            if(s==1)
            booking.setBookingStatus("a");
            else
                if(s==2)
                    booking.setBookingStatus("r");
            bookingService.save(booking);
        }

}
