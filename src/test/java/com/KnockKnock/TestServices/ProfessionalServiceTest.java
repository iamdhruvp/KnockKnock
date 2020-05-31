package com.KnockKnock.TestServices;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.ProfessionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProfessionalServiceTest {

    @Autowired
    private ProfessionalService professionalService;

    @MockBean
    private ProfessionalRepository professionalRepository;

    @Test
    void save() throws Exception{

        Date date=new Date();

        UserRole userRole=new UserRole("professional");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);

        Professional professional =new Professional("anmol","F","anmol@gmail.com" , login,"1234567","2010-1-1",2);

        when(professionalRepository.save(professional)).thenReturn(
                professional
        );

    }

    @Test
    void findById(){
        long id=1;


        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);

        when(professionalRepository.findById(id)).thenReturn(
                java.util.Optional.of(new Professional("anmol", "f", "anmol@gmail.com", login,"123456","2010-1-1",1)
        ));
        assertEquals("anmol", professionalService.findById(id).getProfessionalName());


    }

    @Test
    void findAll(){

        Date date=new Date();

        UserRole userRole=new UserRole("professional");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);
        when(professionalRepository.findAll()).thenReturn((List<Professional>) Stream.of(

 new Professional("henil", "f", "henil@gmail.com", login,"123456","2010-1-1",1)
        ).collect((Collectors.toList())));

        assertEquals(1 , professionalService.findAll().size());
    }


}
