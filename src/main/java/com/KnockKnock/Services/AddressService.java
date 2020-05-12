package com.KnockKnock.Services;

import com.KnockKnock.Entities.Address;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


}
