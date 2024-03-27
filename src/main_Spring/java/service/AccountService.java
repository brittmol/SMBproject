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

    public Account register(Account account) {
        Account accountExists = accountRepository.findByUsername(account.getUsername()).orElse(null);

        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && accountExists == null) {
            return accountRepository.save(account);
        }
        return null;
    }

    public Account login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).orElse(null);
    }

    public Account getAccount(int id) {
        return accountRepository.findById(id).orElse(null);
    }

}
