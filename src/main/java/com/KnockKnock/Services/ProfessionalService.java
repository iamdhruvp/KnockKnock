package com.KnockKnock.Services;

import com.KnockKnock.Entities.Login;

import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProfessionalService {

    @Autowired
    ProfessionalRepository professionalRepository;


    public Professional findByLogin(Login log) {


        return professionalRepository.findByLogin(log);
    }

    public List<Professional> findByProfessionalIdIn(List<Long> pIDs){
        System.out.println(pIDs);
        return professionalRepository.findByProfessionalIdIn( pIDs);
    }
    public List<Professional> findAll(){
        return professionalRepository.findAll();

    }
    public Professional findById(Long id)
    {
        return professionalRepository.findById(id).orElse(null);
    }

    public void save(Professional professional)
    {
        professionalRepository.save(professional);
    }

    public Professional findByProfessionalId(Long pid) {
        return professionalRepository.findByProfessionalId(pid);
    }


}
