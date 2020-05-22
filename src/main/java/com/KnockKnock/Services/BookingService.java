package com.KnockKnock.Services;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    public List<Booking> findByCustomerCustomerId(Long cID){
        System.out.println(cID);
        return bookingRepository.findByCustomerCustomerId( cID);
    }

    public Booking save(Booking booking)
    {
        return bookingRepository.save(booking);
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }
}
