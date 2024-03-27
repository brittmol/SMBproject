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
        // Implement logic to retrieve all messages
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        // Implement logic to retrieve message by ID
        return messageRepository.findById(messageId);
    }

    public List<Message> getMessagesByUserId(int userId) {
        // Implement logic to retrieve messages by user ID
        return messageRepository.findByUserId(userId);
    }

    public Message createMessage(Message message) {
        // Implement message creation logic here
        String text = message.getMessage_text();
        if(!text.isEmpty() && text.length() <= 255 && text != null) {
            return messageRepository.save(message);
        }
        return null;
    }

    public Message updateMessageById(int messageId, Message messageUpdated) {
        // Implement logic to update message text
        String updatedText = messageUpdated.getMessage_text();
        if(!updatedText.isEmpty() && updatedText.length() <= 255 && updatedText != null) {
            boolean result = messageRepository.updateMessageById(messageId, updatedText);
            if(result) {
                return messageRepository.findById(messageId);
            }
        }
        return null;
    }

    public deleteMessageById(int messageId) {
        // Implement logic to delete message by ID
        Message deletedMessage = messageRepository.findById(messageId);
        boolean result = messageRepository.deleteById(messageId);
        if(result) {
            return deletedMessage;
        } else {
            return null;
        }
    }

}
