package com.KnockKnock.Services;

import com.KnockKnock.Entities.ServiceCategory;
import com.KnockKnock.Repositories.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    public Iterable<ServiceCategory> findAll()
    {
        return serviceCategoryRepository.findAll();
    }
}
