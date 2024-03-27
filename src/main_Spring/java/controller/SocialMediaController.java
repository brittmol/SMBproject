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
