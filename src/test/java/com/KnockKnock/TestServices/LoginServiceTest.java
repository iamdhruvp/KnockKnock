package com.KnockKnock.TestServices;


import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.LoginRepository;
import com.KnockKnock.Services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private LoginRepository loginRepository;

    @Test
    void save() throws Exception{

        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        Login login=new Login(date,date,"9993308871","Abcd@1234",'a',userRole);

        when(loginRepository.save(login)).thenReturn(
                login
        );

    }

    @Test
    void findByMobileNo()
    {
        String mobileNo="9993308871";

        Date date=new Date();

        UserRole userRole=new UserRole("customer");

        when(loginRepository.findByMobileNo(mobileNo)).thenReturn(
               new Login(date,date,"9993308871","Abcd@1234",'a',userRole)
        );
        assertEquals("Abcd@1234", loginService.findByMobileNo(mobileNo).getPassword());
    }
}
