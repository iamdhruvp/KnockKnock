package com.KnockKnock.Services;

import com.KnockKnock.Entities.ProfessionalService;
import com.KnockKnock.Repositories.ProfessionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalServiceService {

    @Autowired
    private ProfessionalServiceRepository professionalServiceRepository;
    public List<ProfessionalService> findAll()
    {
        return professionalServiceRepository.findAll();
    }
    public List<Long> findProfessionalIdGivenServices(List<Long> serviceIDs){
        Integer len = serviceIDs.size();
        return professionalServiceRepository.findProfessionalIdGivenServices( serviceIDs, Long.valueOf(len));
    }
}
