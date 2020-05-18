package com.KnockKnock.Repositories;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLogin(Login login);
}
