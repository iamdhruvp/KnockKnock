package com.KnockKnock.Services;


import com.KnockKnock.Entities.BankAccount;
import com.KnockKnock.Repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void save(BankAccount bank)
    {
        bankAccountRepository.save(bank);
    }
}
