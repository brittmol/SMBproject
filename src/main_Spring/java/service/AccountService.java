package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        // Implement registration logic here
        return accountRepository.save(account);
    }

    public Account login(Account account) {
        // Implement login logic here
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}

