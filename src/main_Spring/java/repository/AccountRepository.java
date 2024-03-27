package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // You can add custom query methods here if needed
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
