package com.KnockKnock.Services;


import com.KnockKnock.Entities.UserRole;
import com.KnockKnock.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public void save(UserRole userRole)
    {
        userRoleRepository.save(userRole);
    }
}
