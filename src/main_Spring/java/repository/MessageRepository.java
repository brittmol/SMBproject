package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    // You can add custom query methods here if needed
}
