package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Entities.ProfessionalService;
import com.KnockKnock.Services.ProfessionalServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ProfessionalServiceController {

   @Autowired
   private ProfessionalServiceService professionalServiceService;

   @Autowired
   com.KnockKnock.Services.ProfessionalService professionalService = new com.KnockKnock.Services.ProfessionalService();

    @GetMapping( value = "/getProfessionalService")
    public ResponseEntity<String> getProfessionalService() {
        System.out.println("I am feeling danger.........");
        try {
//            ProfessionalService professionalService = new ProfessionalService();
            Iterable<ProfessionalService> data = professionalServiceService.findAll();
            Integer counter=0;
            System.out.println("Init"+counter);
            for (Object i : data) {
                counter++;
            }
            System.out.println("Final"+counter);


            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            System.out.println(om.writeValueAsString(data));
           /* for(ProfessionalService o : data){
                System.out.println(om.writeValueAsString(data));
            }*/

            /*ObjectMapper mapper = new ObjectMapper();

            FilterProvider filters = new SimpleFilterProvider() .addFilter(
                    "professionalNameOnly", SimpleBeanPropertyFilter.filterOutAllExcept("login"));

            for(ProfessionalService o : data){
                String jsonString = mapper.setFilterProvider(filters)
                       //.withDefaultPrettyPrinter()
                        .writeValueAsString(data);
                System.out.println(jsonString);
            }*/

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(data));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/getProfessional")
    public ResponseEntity<String> getProfessionalService2(@RequestBody List<Long> serviceIDs) {
        System.out.println("I am feeling more danger.........");
        try {

            System.out.println(serviceIDs);

            List<Long> pIDs = professionalServiceService.findProfessionalIdGivenServices(serviceIDs);
            int counter = 0;
            for (Object i : pIDs) {
                counter++;
            }
            System.out.println("Final"+ counter);

            List<Professional> p = professionalService.findByProfessionalIdIn(pIDs);

            System.out.println(p);

            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId", "professionalName", "professionalGender", "professionalEmail",
                            "professionalServices", "professionalExperience", "servingCity", "customerPhoto"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            System.out.println(om.writeValueAsString(p));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(p));
        } catch (Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
