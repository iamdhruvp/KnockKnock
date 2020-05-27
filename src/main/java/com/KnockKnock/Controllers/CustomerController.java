package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.*;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
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

   @Autowired
   private ProfessionalService professionalService;

    @PostMapping( value = "/postCustomer")
    public ResponseEntity<Long> postCustomer( @RequestBody Customer_Login cl  ) {
        System.out.println("I am posting a customer.........");
        Date date=new Date();

        UserRole ur=userRoleService.findById(1);


        Login login=new Login(date,date,cl.getMobileNo(),cl.getPassword(),'a',ur);



        loginService.save(login);
        Customer customer=new Customer(cl.getCustomerName(),cl.getCustomerGender(),cl.getCustomerEmail() , login);

        customerService.save(customer);

        return new ResponseEntity<Long>(customer.getCustomerId(), HttpStatus.OK);

    }

    @GetMapping(value="/getaddress/{id}/{rid}")
    public ResponseEntity<Address_City>  getAddress(@PathVariable("id") Long id, @PathVariable("rid") Long rid)
    {
        Address_City ac=new Address_City();
try {
    if(rid==1) {
        Customer cus = customerService.findById(id);
        Address ad = cus.getAddress();
        City c = ad.getAddressCity();
       ac = new Address_City(ad.getAddressName(), ad.getAddressLine(), ad.getAddressLandmark()
                , ad.getAddressPincode(), c.getCityName(), c.getCityState(), c.getCityCountry(), ad.getIsDefaultAddress());

    }
    else
        if(rid==2)
        {
            Professional professional=professionalService.findById(id);
            Address ad = professional.getAddress();
            City c = ad.getAddressCity();
            ac = new Address_City(ad.getAddressName(), ad.getAddressLine(), ad.getAddressLandmark()
                    , ad.getAddressPincode(), c.getCityName(), c.getCityState(), c.getCityCountry(), ad.getIsDefaultAddress());



        }
    return new ResponseEntity<Address_City>(ac, HttpStatus.OK);
}
catch (Exception e) {
    return new ResponseEntity<Address_City>(HttpStatus.BAD_REQUEST);

}
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
