package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Service;
import com.KnockKnock.Entities.ServiceSubCategory;
import com.KnockKnock.Services.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ServiceController {

    public static final Logger logger = LogManager.getLogger(ServiceController.class);

   @Autowired
   private ServiceService serviceService;

    @GetMapping( value = "/getService/{id}")
    public ResponseEntity<Iterable<Service>> getService(@PathVariable(value="id") Long subCategoryId) {
        logger.info("I am fetching Services.........");
        try {
            ServiceSubCategory serviceSubCategory = new ServiceSubCategory();
            serviceSubCategory.setServiceSubCategoryId(subCategoryId);
            return new ResponseEntity<Iterable<Service>>(serviceService.findByServiceSubCategory(serviceSubCategory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Service>>(HttpStatus.BAD_REQUEST);
        }

    }
}
