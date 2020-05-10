package com.KnockKnock.Services;

import com.KnockKnock.Entities.ServiceCategory;
import com.KnockKnock.Entities.ServiceSubCategory;
import com.KnockKnock.Repositories.ServiceSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    @Autowired
    private ServiceSubCategoryRepository serviceSubCategoryRepository;
    public Iterable<ServiceSubCategory> findByServiceCategory(ServiceCategory serviceCategory)
    {
        return serviceSubCategoryRepository.findByServiceCategory(serviceCategory);
    }
}
