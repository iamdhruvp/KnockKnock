package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.*;
import com.KnockKnock.Repositories.AddressRepository;
import com.KnockKnock.Repositories.CityRepository;
import com.KnockKnock.Repositories.CustomerRepository;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.AddressService;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class AddressController {

    public static final Logger logger = LogManager.getLogger(BankController.class);

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

        Integer is=a.getIsDefaultAddress();


        List<City> city;

        city=cityRepository.findAll();
        try{
        for (City c : city) {
            if ((a.getCityName().equals(c.getCityName())) && (a.getCityState().equals(c.getCityState())) &&
                    (a.getCityCountry().equals(c.getCityCountry()))) {
                logger.info("address details to be saved");

                Address ad = new Address(a.getAddressName(), a.getAddressLine(),
                        a.getAddressLandmark(), a.getAddressPincode(), c, a.getIsDefaultAddress());


                //addressRepository.save(ad);
                if (rid == 1) {
                    Customer cus = customerService.findById(id);
                    cus.setAddress(ad);
                    customerRepository.save(cus);
                    logger.info("Saved address details for this customer "+cus.getCustomerName());
                } else if (rid == 2) {

                    Professional professional = professionalService.findById(id);
                   // addressRepository.save(ad);

                    professional.setAddress(ad);
                    professionalService.save(professional);
                    logger.info("Saved address details for this professional "+professional.getProfessionalName());
                }
            }

        }



        }
        catch (Exception e)
        {
            return "{\"status\":false}";
        }


        return "{\"status\":true}";

    }







}
