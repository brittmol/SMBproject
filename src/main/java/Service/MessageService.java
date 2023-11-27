package service;

import java.util.*;
import dao.MessageDAO;
import model.Message;

public class MessageService {

    // add dependency
    MessageDAO messageDAO;
    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    // add other stuff
    public ArrayList<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public ArrayList<Message> getAllUserMessages(int posted_by) {
        return messageDAO.getAllUserMessages(posted_by);
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message addMessage(Message messageToAdd) {
        return messageDAO.addMessage(messageToAdd.getPosted_by(), messageToAdd.getMessage_text());
    }

    public Message updateMessageById(int message_id, String message_text) {
        boolean result = messageDAO.updateMessageById(message_id, message_text);
        if (result) {
            return messageDAO.getMessageById(message_id);
        } else {
            return null;
        }
    }

    public Message deleteMessageById(int message_id) {
        boolean result = messageDAO.deleteMessageById(message_id);
        if (result) {
            return messageDAO.getMessageById(message_id);
        } else {
            return null;
        }
    }

}
