package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void register(Account account) {
        ArrayList<String> allAccounts = new ArrayList<>(accountRepository.findAll());
        boolean usernameExists = allAccounts.contains(account.getUsername());
        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && !usernameExists) {
            return accountRepository.save(account);
        }
    }

    public void login(Account account) {
        accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    // public Account register(Account account) {
    //     ArrayList<String> allAccounts = new ArrayList<>(accountRepository.findAll());
    //     boolean usernameExists = allAccounts.contains(account.getUsername());
    //     if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && !usernameExists) {
    //         return accountRepository.save(account);
    //     }
    //     return null;
    // }


}
