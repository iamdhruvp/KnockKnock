package com.KnockKnock.Services;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
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

    public Customer findById(Long id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer findByLogin(Login log){
        return customerRepository.findByLogin(log);
    }
}
