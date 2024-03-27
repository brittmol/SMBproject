package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        // Implement registration logic here
        ArrayList<String> allAccounts = new ArrayList<>(accountRepository.getAllUsernames());
        boolean usernameExists = allAccounts.contains(account.getUsername());
        if(account.getUsername().length() > 0 && account.getPassword().length() >= 4 && !usernameExists) {
            return accountRepository.createAccount(account);
        }
        return null;
    }

    public Account login(Account account) {
        // Implement login logic here
        return accountRepository.getAccount(account.getUsername(), account.getPassword());
    }

}
