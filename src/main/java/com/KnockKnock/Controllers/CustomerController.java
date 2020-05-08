package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Customer_Login;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController("/customer")
public class CustomerController<LoginService> {

    @Autowired
    private CustomerService customerService;

   @Autowired
   private  com.KnockKnock.Services.LoginService loginService;

   @Autowired
  private UserRoleService userRoleService;

    @PostMapping( value = "/postCustomer")
    public String postCustomer( @RequestBody Customer_Login cl  ) {
        System.out.println("I am posting a customer.........");
        Date date=new Date();

        UserRole ur=userRoleService.findById(1);


        Login login=new Login(date,date,cl.getMobileNo(),cl.getPassword(),'a',ur);



        loginService.save(login);
        Customer customer=new Customer(cl.getCustomerName(),cl.getCustomerGender(),cl.getCustomerEmail() , login);

        customerService.save(customer);

        return "{\"status\":true, \"user\":{\"mobileNo\":\"" + cl.getMobileNo() + "\", \"password\":\"" + cl.getPassword() + "\"}}";

    }
}
