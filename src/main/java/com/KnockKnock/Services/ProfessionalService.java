package com.KnockKnock.Services;

<<<<<<< HEAD
import com.KnockKnock.Entities.Login;
=======
>>>>>>> e578858610991416c7612058f6dd856f4087a845
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> e578858610991416c7612058f6dd856f4087a845

@Service
public class ProfessionalService {

    @Autowired
<<<<<<< HEAD
    ProfessionalRepository professionalRepository;
    public Professional findByLogin(Login log){


        return professionalRepository.findByLogin(log);
=======
    private ProfessionalRepository professionalRepository;
    public List<Professional> findByProfessionalIdIn(List<Long> pIDs){
        System.out.println(pIDs);
        return professionalRepository.findByProfessionalIdIn( pIDs);
    }
    public List<Professional> findAll(){
        return professionalRepository.findAll();
>>>>>>> e578858610991416c7612058f6dd856f4087a845
    }
}
