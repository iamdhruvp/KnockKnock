package com.KnockKnock.TestServices;


import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.UserRoleRepository;
import com.KnockKnock.Services.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRoleTest {

    @Autowired
    private UserRoleService userRoleService;

    @MockBean
    private UserRoleRepository userRoleRepository;

    @Test
    void save() throws Exception{

        UserRole userRole=new UserRole("customer");


        when(userRoleRepository.save(userRole)).thenReturn(
                userRole
        );

    }

    @Test
    void findById(){
        long id=1;

        when(userRoleRepository.findById(id)).thenReturn(
                java.util.Optional.of(new UserRole("customer"))
        );
        assertEquals("customer", userRoleService.findById(id).getUserRoleName());


    }
}
