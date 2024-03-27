package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import java.util.*;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    // You can add custom query methods here if needed
    // @Query("FROM Message WHERE account_id = :userId")
    // List<Message> findByUserId(@Param("userId") int userId);
    List<Message> findByUserId(int userId);
}
