package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Address;
import com.KnockKnock.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Iterable<Address> findByCustomer(Customer cus);
}
