package com.KnockKnock.Services;

import com.KnockKnock.Entities.ServiceSubCategory;
import com.KnockKnock.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    public Iterable<com.KnockKnock.Entities.Service> findByServiceSubCategory(ServiceSubCategory serviceSubCategory)
    {
        return serviceRepository.findByServiceSubCategory(serviceSubCategory);
    }
}
