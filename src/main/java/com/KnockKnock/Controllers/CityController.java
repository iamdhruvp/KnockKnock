package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.City;
import com.KnockKnock.Entities.ServiceCategory;
import com.KnockKnock.Entities.ServiceSubCategory;
import com.KnockKnock.Services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping( value = "/getCountry")
    public ResponseEntity<List<Object>> getCategory() {

        try {

            List<Object> country=cityService.getAllCountry();

            System.out.println("I am fetching countries.........");

            return new ResponseEntity<List<Object>>(country, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping( value = "/getState/{coun}")
    public ResponseEntity<List<Object>> getState(@PathVariable(value="coun") String count) {
        System.out.println("I am fetching States.........");
        try {
            List<Object> state=cityService.getAllState(count);

            return new ResponseEntity<List<Object>>(state, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping( value = "/getCity/{stat}")
    public ResponseEntity<List<Object>> getCity(@PathVariable(value="stat") String state) {
        System.out.println("I am fetching Cities.........");
        try {
            List<Object> city=cityService.getAllCity(state);

            return new ResponseEntity<List<Object>>(city, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
        }

    }
}
