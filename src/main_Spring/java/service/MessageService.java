package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public List<Message> getMessagesByUserId(int userId) {
        List<Message> messagesByUser = messageRepository.findByUserId(userId);
        return messagesByUser;
    }

    public Message createMessage(Message message) {
        // see if posted_by is found in account

        String text = message.getMessage_text();
        if (!text.isEmpty() && text.length() <= 255 && text != null) {
            return messageRepository.save(message);
        }
        return null;
    }

    public Message updateMessageById(int messageId, Message messageUpdated) {
        Message messageExists = messageRepository.findById(messageId).orElse(null);
        if (messageExists != null) {
            String text = messageUpdated.getMessage_text();
            if (!text.isEmpty() && text.length() <= 255 && text != null) {
                return messageRepository.save(messageUpdated);
            }
        }
        return null;

        // Optional<Message> optionalMessage = messageRepository.findById(messageId);
        // if (optionalMessage.isPresent()) {
        // Message message = optionalMessage.get();
        // String updatedText = messageUpdated.getMessage_text();
        // if (updatedText != null && !updatedText.isEmpty() && updatedText.length() <=
        // 255) {
        // message.setMessage_text(updatedText);
        // return messageRepository.save(message);
        // }
        // }
        // return null;
    }

    public boolean deleteMessageById(int messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        } else {
            return false;
        }
    }

}
