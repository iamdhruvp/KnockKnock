package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.ProfessionalService;
import com.KnockKnock.Services.UserRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController("/prof")
public class ProfessionalController {

    public static final Logger logger = LogManager.getLogger(ProfessionalController.class);

    @Autowired
    private  com.KnockKnock.Services.LoginService loginService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ProfessionalRepository professionalRepository;


    @Autowired
    private ProfessionalService professionalService;

    @PostMapping( value = "/postProfessional")
    public ResponseEntity<Long> postProfessonal(@RequestBody Professional_Login pl) {
        logger.info("a professional signed up");

        try {
            Date date = new Date();


            UserRole ur = userRoleService.findById(2);


            Login login = new Login(date, date, pl.getMobileNo(), pl.getPassword(), 'a', ur);


            loginService.save(login);
            Professional professional = new Professional(pl.getCustomerName(), pl.getCustomerGender(), pl.getCustomerEmail(),
                    login, pl.getProfessionalGSTNo(), pl.getProfessionalBirthDate(), pl.getProfessionalExperience());
            professionalRepository.save(professional);
            logger.info("saved professional........");

            return new ResponseEntity<Long>(professional.getProfessionalId(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);

        }

    }


    @GetMapping(value="/getProfessional/{id}")
    public ResponseEntity<String> getProfessional (@PathVariable("id") Long id)
    {
        try {

            Professional prof = professionalService.findById(id);
            Login log = prof.getLogin();
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId","professionalName", "professionalGender", "professionalEmail",
                            "professionalExperience", "servingCity", "customerPhoto","login"));
            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAll());

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            logger.info("fetching the profile for the professional "+prof.getProfessionalName());
            logger.info("fetching the professional "+log.getMobileNo());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(prof));


        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }



    @GetMapping("/getPList")
    public ResponseEntity<String> getProfessional() {

        List<Long> l = new ArrayList<>();
        l.add(Long.valueOf(1));
        l.add(Long.valueOf(3));

        try{
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();

            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId","professionalName", "professionalGender", "professionalEmail",
                            "professionalServices", "professionalExperience", "servingCity", "customerPhoto"));
            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAllExcept("professional"));

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
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId","professionalName", "professionalGender", "professionalEmail",
                            "professionalServices", "professionalExperience", "servingCity", "customerPhoto"));
            filterProvider.addFilter("professionalOnly",
                    SimpleBeanPropertyFilter.serializeAllExcept("professional"));

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
/*
    @GetMapping(value="/getProfessional/{id}")
    public ResponseEntity<Professional_Login> getCustomer(@PathVariable("id") Long id)
    {
        try {

            Professional professional = professionalService.findById(id);
            Login log = professional.getLogin();
            Professional_Login pl = new Professional_Login(professional.getProfessionalName(),
                    professional.getProfessionalGender(),
                   professional.getProfessionalEmail(), log.getPassword(), log.getMobileNo(), professional.getProfessionalGSTNo(),
                    professional.getProfessionalBirthDate(),professional.getProfessionalExperience());

            logger.info("fetching the profile for the Professional "+pl.getCustomerName());
            return new ResponseEntity<Professional_Login>(pl, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<Professional_Login>(HttpStatus.BAD_REQUEST);

        }
*/
    }
}
