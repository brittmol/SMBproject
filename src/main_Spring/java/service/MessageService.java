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

    public List<Message> getAllMessages() {
        // Implement logic to retrieve all messages
        return messageRepository.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        // Implement logic to retrieve message by ID
        return messageRepository.getMessageById(messageId);
    }

    public List<Message> getMessagesByUserId(int userId) {
        // Implement logic to retrieve messages by user ID
        return messageRepository.getMessagesByUserId(userId);
    }

    public Message createMessage(Message message) {
        // Implement message creation logic here
        String text = message.getMessage_text();
        if(!text.isEmpty() && text.length() <= 255 && text != null) {
            return messageRepository.createMessage(message);
        }
        return null;
    }

    public Message updateMessage(int messageId, Message messageUpdated) {
        // Implement logic to update message text
        String updatedText = messageUpdated.getMessage_text();
        if(!updatedText.isEmpty() && updatedText.length() <= 255 && updatedText != null) {
            boolean result = messageRepository.updateMessageById(messageId, updatedText);
            if(result) {
                return messageRepository.getMessageById(messageId);
            }
        }
        return null;
    }

    public deleteMessage(int messageId) {
        // Implement logic to delete message by ID
        Message deletedMessage = messageRepository.getMessageById(messageId);
        boolean result = messageRepository.deleteMessageById(messageId);
        if(result) {
            return deletedMessage;
        } else {
            return null;
        }
    }

}
