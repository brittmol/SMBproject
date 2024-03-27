package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account registeredAccount = accountService.register(account);
        if (registeredAccount != null) {
            return ResponseEntity.status(HttpStatus.OK).body(registeredAccount);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account loggedInAccount = accountService.login(account);
        if (loggedInAccount != null) {
            return ResponseEntity.status(HttpStatus.OK).body(loggedInAccount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") int messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable("account_id") int userId) {
        List<Message> messages = messageService.getMessagesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Account existingAccount = accountService.getAccount(message.getPosted_by());
        if (existingAccount != null) {
            Message createdMessage = messageService.createMessage(message);
            if (createdMessage != null) {
                return ResponseEntity.status(HttpStatus.OK).body(createdMessage);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable("message_id") int messageId) {
        boolean deleted = messageService.deleteMessageById(messageId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable("message_id") int messageId,
            @RequestBody Message updatedMessage) {
        Message updated = messageService.updateMessageById(messageId, updatedMessage);
        if (updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
