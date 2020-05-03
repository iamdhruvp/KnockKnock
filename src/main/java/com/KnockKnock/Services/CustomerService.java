package com.KnockKnock.Services;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer)
    {
        customerRepository.save(customer);
    }
}
