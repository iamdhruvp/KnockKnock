package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.ProfessionalService;
import com.KnockKnock.Services.ProfessionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ProfessionalServiceController {

   @Autowired
   private ProfessionalServiceService professionalServiceService;

    @GetMapping( value = "/getProfessionalService")
    public ResponseEntity<Iterable<ProfessionalService>> getProfessionalService() {
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
            return new ResponseEntity<Iterable<ProfessionalService>>(professionalServiceService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ProfessionalService>>(HttpStatus.BAD_REQUEST);
        }

    }

   /*@GetMapping( value = "/getProfessionalService/{id}")
    public ResponseEntity<Iterable<ServiceSubCategory>> getSubCategory(@PathVariable(value="id") Integer categoryId) {
        System.out.println("I am fetching SubCategories.........");
        try {
            ServiceCategory serviceCategory = new ServiceCategory();
            serviceCategory.setServiceCategoryId(categoryId);
            return new ResponseEntity<Iterable<ServiceSubCategory>>(subCategoryService.findByServiceCategory(serviceCategory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ServiceSubCategory>>(HttpStatus.BAD_REQUEST);
        }

    }*/
}
