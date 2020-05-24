package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController("/prof")
public class ProfessionalController {

    @Autowired
    private  com.KnockKnock.Services.LoginService loginService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @PostMapping( value = "/postProfessional")
    public ResponseEntity<Professional_Login> postProfessonal(@RequestBody Professional_Login pl) {
        System.out.println("I am posting a professional........");

        try {
            Date date = new Date();


            UserRole ur = userRoleService.findById(2);


            Login login = new Login(date, date, pl.getMobileNo(), pl.getPassword(), 'a', ur);


            loginService.save(login);
            Professional professional = new Professional(pl.getCustomerName(), pl.getCustomerGender(), pl.getCustomerEmail(),
                    login, pl.getProfessionalGSTNo(), pl.getProfessionalBirthDate(), pl.getProfessionalExperience());

            professionalRepository.save(professional);

            return new ResponseEntity<Professional_Login>(pl, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Professional_Login>(HttpStatus.BAD_REQUEST);

        }

    }




}
