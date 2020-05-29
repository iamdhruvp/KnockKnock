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
    public List<Booking> findAllByCustomerCustomerId(Long cID){
        System.out.println(cID);
        return bookingRepository.findAllByCustomerCustomerId( cID);
    }

    public Booking findById(Long id)
    {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> findAllByCustomerCustomerIdAndBookingStatusIn(Long cID, List<String> status){
        System.out.println(cID);
        return bookingRepository.findAllByCustomerCustomerIdAndBookingStatusIn( cID, status);
    }

    public Booking save(Booking booking)
    {
        return bookingRepository.save(booking);
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public List<Booking> findByProfessionalId(Long id)
    {
        return bookingRepository.findByProfessionalId(id);
    }

}
