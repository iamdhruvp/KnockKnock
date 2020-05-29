package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Entities.ProfessionalService;
import com.KnockKnock.Services.ProfessionalServiceService;
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

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ProfessionalServiceController {

    public static final Logger logger = LogManager.getLogger(ProfessionalServiceController.class);

   @Autowired
   private ProfessionalServiceService professionalServiceService;

   @Autowired
   com.KnockKnock.Services.ProfessionalService professionalService = new com.KnockKnock.Services.ProfessionalService();

    @GetMapping( value = "/getProfessionalService")
    public ResponseEntity<String> getProfessionalService() {
        logger.info("fetching services to be displayed");
        try {
//            ProfessionalService professionalService = new ProfessionalService();
            Iterable<ProfessionalService> data = professionalServiceService.findAll();
            Integer counter=0;
            logger.info("Initial counter size "+counter);
            for (Object i : data) {
                counter++;
            }
            logger.info("Final counter size for services "+counter);


            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalName"));

            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            logger.info("the data for service "+om.writeValueAsString(data));
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

        try {



            List<Long> pIDs = professionalServiceService.findProfessionalIdGivenServices(serviceIDs);
            int counter = 0;
            for (Object i : pIDs) {
                counter++;
            }
            logger.info("counter for professional "+ counter);

            List<Professional> p = professionalService.findByProfessionalIdIn(pIDs);


//            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//            filterProvider.addFilter("professionalNameOnly",
//                    SimpleBeanPropertyFilter.filterOutAllExcept("professionalId", "professionalName", "professionalGender", "professionalEmail",
//                            "professionalServices", "professionalExperience", "servingCity", "customerPhoto"));

            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("professionalOnly",
                        SimpleBeanPropertyFilter.serializeAllExcept("professional"));
            filterProvider.addFilter("professionalNameOnly",
                    SimpleBeanPropertyFilter.serializeAll());


            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(filterProvider);

            logger.info(om.writeValueAsString(p)+" is the professional");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(om.writeValueAsString(p));
        } catch (Exception e) {
           logger.error("error");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
