package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.LoginRepository;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.LoginService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class LoginController {


    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LoginService loginService;

    @Autowired
    CustomerService customerService;



    @PostMapping("/verifyCustomer/{role}")
    public ResponseEntity<Long> verifyCustomer(@RequestBody Login login, @PathVariable("role") Long roleId) {

String mobile=login.getMobileNo();
String password=login.getPassword();

System.out.println(mobile+"..................");
        System.out.println(password+"..................");
        Login log=loginService.findByMobileNo(mobile);
        UserRole role=userRoleService.findById(roleId);

        if(log!=null && role!=null)
        {
            if((password).equals(log.getPassword()) && (role).equals(log.getUserRole()))
            {
                Date date=new Date();
                log.setLastLoginDate(date);
                Customer cus=customerService.findByLogin(log);
               Long id=cus.getCustomerId();
                return new ResponseEntity<Long>(id, HttpStatus.OK);
            }
        }

        return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public String verifyCustomer1(@RequestBody String body) {
        System.out.println("I'm in login " + body);

        return "{\"status\":true, \"user\":"+ body +"}";

        /*List<Login> log;
        log = loginRepository.findAll();

        UserRole role=userRoleService.findById(roleId);

        for (Login l : log) {

            if ((mobile).equals(l.getMobileNo()) && (password).equals(l.getPassword()) && (role).equals(l.getUserRole())) {

                Date date=new Date();
                l.setLastLoginDate(date);
                return "success";
            }
        }
        return "failed";*/
    }
}
