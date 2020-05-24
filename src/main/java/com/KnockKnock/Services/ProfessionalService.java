package com.KnockKnock.Services;

import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfessionalService {

    @Autowired
    ProfessionalRepository professionalRepository;
    public Professional findByLogin(Login log){


        return professionalRepository.findByLogin(log);
    }
}
