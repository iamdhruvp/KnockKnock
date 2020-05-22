package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Booking;
import com.KnockKnock.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerCustomerId(Long cID);
}
