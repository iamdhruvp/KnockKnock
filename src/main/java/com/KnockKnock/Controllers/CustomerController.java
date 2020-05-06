package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Customer_Login;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("/customer")
public class CustomerController<LoginService> {

    @Autowired
    private CustomerService customerService;

   @Autowired
   private  com.KnockKnock.Services.LoginService loginService;

   @Autowired
  private UserRoleService userRoleService;

    @PostMapping( value = "/postCustomer")
    public void someControllerMethod( @RequestBody Customer_Login cl  ) {
        System.out.println("I am here");
        Date date=new Date();

        UserRole ur=userRoleService.findById(1);


        Login login=new Login(date,date,cl.getMobileNo(),cl.getPassword(),'a',ur);



        loginService.save(login);
        Customer customer=new Customer(cl.getCustomerName(),cl.getCustomerGender(),cl.getCustomerEmail() , login);

        customerService.save(customer);

    }


     /*@PostMapping(value = "/postCustomer")
         public void postCustomer(@RequestParam("cname") String cn, @RequestParam("cgen") String cgen,
                                  @RequestParam("cmail") String cmail,
                                  @RequestParam("cpass") String password, @RequestParam("cmobile") String mobile)

    {
        System.out.println("I'm here");
        Date date=new Date();

        UserRole ur=userRoleService.findById(1);


        Login login=new Login(date,date,mobile,password,'a',ur);



        loginService.save(login);
        Customer customer=new Customer(cn,cgen,cmail , login);

        customerService.save(customer);




    }*/
}
