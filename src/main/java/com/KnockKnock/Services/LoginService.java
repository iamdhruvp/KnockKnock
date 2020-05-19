package com.KnockKnock.Services;


import com.KnockKnock.Entities.Login;
import com.KnockKnock.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public void save(Login login)
    {
        loginRepository.save(login);
    }
    public Login findByMobileNo(String mobile)
    {
        return loginRepository.findByMobileNo(mobile);
    }
}
