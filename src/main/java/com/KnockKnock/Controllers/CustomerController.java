package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/postCustomer")
            void postCustomer(@RequestParam("cname") String cn,@RequestParam("cgen") String cgen,
                              @RequestParam("cmail") String cmail,
                              @RequestParam("cpass") String password, @RequestParam("cmobile") String mobile)

    {
        Date date=new Date();

        UserRole ur=new UserRole("Customer");

        userRoleService.save(ur);

        Login login=new Login(date,date,mobile,password,'a', ur);

        loginService.save(login);
        Customer customer=new Customer(cn,cgen,cmail , login);

        customerService.save(customer);




    }
}
