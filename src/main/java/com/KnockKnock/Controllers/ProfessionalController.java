package com.KnockKnock.Controllers;

<<<<<<< HEAD
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




=======

import com.KnockKnock.Services.ProfessionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ProfessionalController {

    @Autowired
    ProfessionalService professionalService = new ProfessionalService();




    @GetMapping("/getPList")
    public ResponseEntity<String> getProfessional() {

        List<Long> l = new ArrayList<>();
        l.add(Long.valueOf(1));
        l.add(Long.valueOf(3));

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName", "professionalGender", "professionalEmail",
                            "professionalServices", "professionalExperience", "servingCity", "customerPhoto"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(professionalService.findByProfessionalIdIn(l)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getP")
    public ResponseEntity<String> getProfessionals() {
        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.serializeAll());

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(professionalService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
>>>>>>> e578858610991416c7612058f6dd856f4087a845
}
