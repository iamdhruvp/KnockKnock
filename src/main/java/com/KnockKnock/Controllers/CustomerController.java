package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.*;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Customer_Login> postCustomer( @RequestBody Customer_Login cl  ) {
        System.out.println("I am posting a customer.........");
        Date date=new Date();

        UserRole ur=userRoleService.findById(1);


        Login login=new Login(date,date,cl.getMobileNo(),cl.getPassword(),'a',ur);



        loginService.save(login);
        Customer customer=new Customer(cl.getCustomerName(),cl.getCustomerGender(),cl.getCustomerEmail() , login);

        customerService.save(customer);

        return new ResponseEntity<Customer_Login>(cl, HttpStatus.OK);

    }

    @GetMapping(value="/getaddress")
    public ResponseEntity<Address_City>  getAddress()
    {

        System.out.println("here i your addresss....");
        Customer cus=customerService.findById((long)1);
        Address ad=cus.getAddress();
        City c=ad.getAddressCity();
        Address_City ac=new Address_City(ad.getAddressName() ,ad.getAddressLine(),ad.getAddressLandmark()
                ,ad.getAddressPincode(),c.getCityName(),c.getCityState(),c.getCityCountry(),ad.getDefaultAddress());


        return new ResponseEntity<Address_City>(ac, HttpStatus.OK);
    }


    @GetMapping(value="/getcustomer/{id}")
    public ResponseEntity<Customer_Login> getCustomer(@PathVariable("id") Long id)
    {
        try {
            Customer cus = customerService.findById(id);
            Login log = cus.getLogin();
            Customer_Login cl = new Customer_Login(cus.getCustomerName(), cus.getCustomerGender(), cus.getCustomerEmail(), log.getPassword(), log.getMobileNo());
            return new ResponseEntity<Customer_Login>(cl, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Customer_Login>(HttpStatus.BAD_REQUEST);

        }

    }
}
