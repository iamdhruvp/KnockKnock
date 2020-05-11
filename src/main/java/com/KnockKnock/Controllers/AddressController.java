package com.KnockKnock.Controllers;

import com.KnockKnock.Entities.Address;
import com.KnockKnock.Entities.Address_City;
import com.KnockKnock.Entities.City;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Repositories.AddressRepository;
import com.KnockKnock.Repositories.CityRepository;
import com.KnockKnock.Repositories.CustomerRepository;
import com.KnockKnock.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping(value="postAddress/{c_id}")
    public String postAddress(@RequestBody Address_City a, @PathVariable("c_id") Long id) {
        System.out.println("I am posting a customer.........");
        System.out.println(a.getCityCountry());


        List<City> city;

        city=cityRepository.findAll();

        try{

        Customer cus = customerService.findById((long)1);

        System.out.println(cus.getCustomerName());



            for (City c : city) {
                if ((a.getCityName().equals(c.getCityName())) && (a.getCityState().equals(c.getCityState())) &&
                        (a.getCityCountry().equals(c.getCityCountry()))) {
                    System.out.println("good to goo...................");

                    Address ad = new Address(a.getAddressName(), a.getAddressLine(),
                            a.getAddressLandmark(), a.getAddressPincode(), c, a.getDefaultAddress());

                    System.out.println("address to be save..................");
                    //addressRepository.save(ad);
                    customerRepository.save(cus);
                    System.out.println("saved..................");
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
