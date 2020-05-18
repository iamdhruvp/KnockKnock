package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.ServiceCategory;
import com.KnockKnock.Entities.ServiceSubCategory;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Services.LoginService;
import com.KnockKnock.Services.SubCategoryService;
import com.KnockKnock.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class SubCategoryController {

   @Autowired
   private SubCategoryService subCategoryService;


    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LoginService loginService;

    @GetMapping( value = "/getSubCategory/{id}")
    public ResponseEntity<Iterable<ServiceSubCategory>> getSubCategory(@PathVariable(value="id") Integer categoryId) {
        System.out.println("I am fetching SubCategories.........");
        try {
            ServiceCategory serviceCategory = new ServiceCategory();
            serviceCategory.setServiceCategoryId(categoryId);
            return new ResponseEntity<Iterable<ServiceSubCategory>>(subCategoryService.findByServiceCategory(serviceCategory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ServiceSubCategory>>(HttpStatus.BAD_REQUEST);
        }

    }

}
