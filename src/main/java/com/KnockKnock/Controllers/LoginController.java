package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.LoginRepository;
import com.KnockKnock.Services.LoginService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LoginService loginService;




    @GetMapping("/verifyCustomer/{role}")
    public String verifyCustomer(@RequestParam("cmobile") String mobile, @RequestParam("cpass") String password,
                               @PathVariable(value="role") long roleId) {


        List<Login> log;
        log = loginRepository.findAll();

        UserRole role=userRoleService.findById(roleId);

        for (Login l : log) {

            if ((mobile).equals(l.getMobileNo()) && (password).equals(l.getPassword()) && (role).equals(l.getUserRole())) {

                Date date=new Date();
                l.setLastLoginDate(date);
                return "success";
            }
        }
        return "failed";
    }

    @PostMapping("/login")
    public String verifyCustomer(@RequestBody String body) {
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
