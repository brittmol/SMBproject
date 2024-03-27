package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) {
        // Implement message creation logic here
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        // Implement logic to retrieve all messages
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        // Implement logic to retrieve message by ID
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        return optionalMessage.orElse(null);
    }

    public List<Message> getMessagesByUserId(Integer userId) {
        // Implement logic to retrieve messages by user ID
        return messageRepository.findByPostedBy(userId);
    }

    public void deleteMessage(Integer messageId) {
        // Implement logic to delete message by ID
        messageRepository.deleteById(messageId);
    }

    public Message updateMessage(Integer messageId, String newMessageText) {
        // Implement logic to update message text
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessage_text(newMessageText);
            return messageRepository.save(message);
        }
        return null;
    }

}
