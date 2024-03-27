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
        return messageRepository.findById(messageId);
    }

    public List<Message> getMessagesByUserId(int userId) {
        List<Message> messagesByUser = messageRepository.findByUserId(userId);
        return messagesByUser;
    }

    public void createMessage(Message message) {
        String text = message.getMessage_text();
        if (!text.isEmpty() && text.length() <= 255 && text != null) {
            return messageRepository.save(message);
        }
    }

    public void updateMessageById(int messageId, Message messageUpdated) {
        Message message = messageRepository.findById(messageId);
        String updatedText = messageUpdated.getMessage_text();
        if (!updatedText.isEmpty() && updatedText.length() <= 255 && updatedText != null) {
            message.setMessage_text(updatedText);
            messageRepository.save(message);
        }
    }

    public void deleteMessageById(int messageId) {
        messageRepository.deleteById(messageId);
    }

    // ------------------------------------

    // public Message createMessage(Message message) {
    // String text = message.getMessage_text();
    // if (!text.isEmpty() && text.length() <= 255 && text != null) {
    // return messageRepository.save(message);
    // }
    // return null;
    // }

    // public Message updateMessageById(int messageId, Message messageUpdated) {
    // // Message message = messageRepository.findById(messageId);
    // // message.setMessage_text(messageUpdated);
    // // messageRepository.save(message);

    // String updatedText = messageUpdated.getMessage_text();
    // if (!updatedText.isEmpty() && updatedText.length() <= 255 && updatedText !=
    // null) {
    // boolean result = messageRepository.updateMessageById(messageId, updatedText);
    // if (result) {
    // return messageRepository.findById(messageId);
    // }
    // }
    // return null;
    // }

    // public deleteMessageById(int messageId) {
    // Message deletedMessage = messageRepository.findById(messageId);
    // boolean result = messageRepository.deleteById(messageId);
    // if(result) {
    // return deletedMessage;
    // } else {
    // return null;
    // }
    // }

}
