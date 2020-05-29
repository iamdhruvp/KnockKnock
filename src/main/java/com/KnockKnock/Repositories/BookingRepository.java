package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByCustomerCustomerId(Long cID);
    List<Booking> findAllByCustomerCustomerIdAndBookingStatusIn(Long cID, List<String> status);

    @Query(value="select * from booking JOIN professional where booking_status='p' AND professional_id=?1", nativeQuery = true)
    List<Booking> findByProfessionalId(Long id);
}
