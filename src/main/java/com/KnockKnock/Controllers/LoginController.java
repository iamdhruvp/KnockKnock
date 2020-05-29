package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.LoginRepository;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.LoginService;
import com.KnockKnock.Services.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class LoginController {

    public static final Logger logger = LogManager.getLogger(LoginController.class);


    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LoginService loginService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProfessionalRepository professionalRepository;



    @PostMapping("/verifyCustomer/{role}")
    public ResponseEntity<Long> verifyCustomer(@RequestBody Login login, @PathVariable("role") Long roleId) {

String mobile=login.getMobileNo();
String password=login.getPassword();

logger.info(mobile+" is the mobile number logged in");
        logger.info(password+" with this password...");
        logger.info(roleId+" is the role..");
        Login log=loginService.findByMobileNo(mobile);
        UserRole role=userRoleService.findById(roleId);


        if(log!=null && role!=null)
        {
            if((password).equals(log.getPassword()) && (role).equals(log.getUserRole()))
            {
                Date date=new Date();
                log.setLastLoginDate(date);
                if(roleId==1) {
                    Customer cus = customerService.findByLogin(log);
                    Long id = cus.getCustomerId();
                    return new ResponseEntity<Long>(id, HttpStatus.OK);
                }
                else
                {
                    Professional professional=professionalRepository.findByLogin(log);
                    Long id=professional.getProfessionalId();
                    return new ResponseEntity<Long>(id, HttpStatus.OK);
                }

            }
        }

        return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
    }



}
