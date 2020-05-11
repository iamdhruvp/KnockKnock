package com.KnockKnock.Services;

import com.KnockKnock.Entities.ProfessionalService;
import com.KnockKnock.Repositories.ProfessionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalServiceService {

    @Autowired
    private ProfessionalServiceRepository professionalServiceRepository;
    public Iterable<ProfessionalService> findAll()
    {
        return professionalServiceRepository.findAll();
    }
}
