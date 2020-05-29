package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.ServiceCategory;
import com.KnockKnock.Services.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class CategoryController {

    public static final Logger logger = LogManager.getLogger(AddressController.class);
   @Autowired
   private CategoryService categoryService;

    @GetMapping( value = "/getCategory")
    public ResponseEntity<Iterable<ServiceCategory>> getCategory() {
        logger.info(" Categories are displayed to customer..");
        try {
            return new ResponseEntity<Iterable<ServiceCategory>>(categoryService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ServiceCategory>>(HttpStatus.BAD_REQUEST);
        }

    }
}
