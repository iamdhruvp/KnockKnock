package com.KnockKnock.Services;

import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;
    public List<Professional> findByProfessionalIdIn(List<Long> pIDs){
        System.out.println(pIDs);
        return professionalRepository.findByProfessionalIdIn( pIDs);
    }
    public List<Professional> findAll(){
        return professionalRepository.findAll();
    }
}
