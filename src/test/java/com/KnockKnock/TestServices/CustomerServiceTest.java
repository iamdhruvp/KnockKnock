package com.KnockKnock.TestServices;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.CustomerRepository;
import com.KnockKnock.Services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CustomerServiceTest {


    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Test
    void save() throws Exception{

        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);

        Customer customer=new Customer("anmol","F","anmol@gmail.com" , login);




        when(customerRepository.save(customer)).thenReturn(
                customer
        );

    }

    @Test
    void findById(){
        long id=1;


        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);

        when(customerRepository.findById(id)).thenReturn(
                java.util.Optional.of(new Customer("anmol", "f", "anmol@gmail.com", login))
        );
        assertEquals("anmol", customerService.findById(id).getCustomerName());


    }


    @Test
    void findByLogin() {

        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);
        when(customerRepository.findByLogin(login)).thenReturn(
                new Customer("anmol", "f", "anmol@gmail.com", login)

        );
        assertEquals("anmol", customerService.findByLogin(login).getCustomerName());
    }
}