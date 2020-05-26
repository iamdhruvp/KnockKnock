package com.KnockKnock.Controllers;


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
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId","professionalName", "professionalGender", "professionalEmail",
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
}
