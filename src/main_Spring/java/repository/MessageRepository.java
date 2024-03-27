package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Add custom query methods here
    @Query("FROM Message WHERE posted_by = :userId")
    List<Message> findByUserId(@Param("userId") int userId);
}
