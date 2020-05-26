package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.AddressRepository;
import com.KnockKnock.Repositories.CityRepository;
import com.KnockKnock.Repositories.CustomerRepository;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.AddressService;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    ProfessionalService professionalService;

    @PostMapping(value="postAddress/{c_id}/{rid}")
    public String postAddress(@RequestBody Address_City a, @PathVariable("c_id") Long id, @PathVariable("rid") Long rid) {
        System.out.println("I am posting a customer.........");
        System.out.println(a.getCityCountry());



        try{
            City c=new City(a.getCityName(),a.getCityState(),a.getCityCountry());

                        Address ad = new Address(a.getAddressName(), a.getAddressLine(),
                                a.getAddressLandmark(), a.getAddressPincode(),c, a.getDefaultAddress());
                        if(rid==1) {
                            Customer cus = customerService.findById(id);
                        cus.setAddress(ad);
                        customerRepository.save(cus);
                        System.out.println("saved..................");
                    }
                        else

                        if(rid==2) {
                            Professional professional=professionalService.findById(id);
                            professional.setAddress(ad);
                            professionalService.save(professional);
                            System.out.println("saved..................");
                        }


        }
        catch (Exception e)
        {
            return "{\"status\":false}";
        }


        return "{\"status\":true}";

    }




}
