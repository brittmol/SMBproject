package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@RestController
@RequestMapping("/api")
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public Account register(@RequestBody Account account) {
        return accountService.register(account);
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account account) {
        return accountService.login(account);
    }

    @GetMapping("/messages")
    public ArrayList<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ArrayList<Message> getMessagesByUserId(@PathVariable int accountId) {
        return messageService.getMessagesByUserId(accountId);
    }

    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PatchMapping("/messages/{id}")
    public Message updateMessageById(@PathVariable int id, @RequestBody Message message) {
        return messageService.updateMessageById(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public Message deleteMessageById(@PathVariable int id) {
        return messageService.deleteMessageById(id);
    }
}


/*
 * do we need to include Response Entity?
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class SocialMediaController {

    // ...

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        // Implement registration endpoint
        Account registeredAccount = accountService.register(account);
        if (registeredAccount != null) {
            return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        // Implement login endpoint
        Account loggedInAccount = accountService.login(account);
        if (loggedInAccount != null) {
            return new ResponseEntity<>(loggedInAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        // Implement create message endpoint
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null) {
            return new ResponseEntity<>(createdMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Implement other endpoints as per requirements
}
