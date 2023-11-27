package Service;

import java.util.*;
import DAO.MessageDAO;
import Model.Message;

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
        String addedMessageText = messageToAdd.getMessage_text();
        // TODO: check if real user --> Account DAO
        if (!addedMessageText.isEmpty() && addedMessageText.length() <= 255 && addedMessageText != null) {
            return messageDAO.addMessage(messageToAdd);
        }
        return null;
        // ALTERNATE FOR SETTER USE --> get id from DAO
            // Integer idFromDAO = messageDAO.addMessage(messageToAdd);
            // messageToAdd.setMessage_id(idFromDAO);
            // return messageToAdd;
    }

    public Message updateMessageById(int message_id, Message messageToUpdate) {
        String updatedMessageText = messageToUpdate.getMessage_text();
        if (!updatedMessageText.isEmpty() && updatedMessageText.length() <= 255 && updatedMessageText != null) {
            // If the new message_text is blank or exceeds 255 characters, return a 400 response.
            boolean result = messageDAO.updateMessageById(message_id, updatedMessageText);
            if (result) {
                return messageDAO.getMessageById(message_id);
            }
        }
        return null;
    }

    public Message deleteMessageById(int message_id) {
        Message deletedMessage = messageDAO.getMessageById(message_id);
        boolean result = messageDAO.deleteMessageById(message_id);
        if (result) {
            return deletedMessage;
        } else {
            return null;
        }
    }


}
