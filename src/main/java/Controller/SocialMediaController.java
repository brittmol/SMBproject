package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;

import model.Message;
import service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */

/*
 * TESTS
 * CreateMessage
 * DeleteMessageByMessageId
 * RetrieveAllMessagesForUser
 * RetrieveAllMessages
 * RetrieveMessageByMessageId
 * UpdateMessageText
 * UserLogin
 * UserRegistration
 *
 *
 */

public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     *
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        // Home page
        app.get("/", ctx -> {
            System.out.println("Home Page here :) ");
            ctx.result("Home Page here");
        });

        // GET all, GET all by user, GET by id
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{userId}", this::getAllUserMessages); // TODO: fix path
        app.get("/messages/{id}", this::getMessageById);

        // POST, PUT, DELETE
        app.post("/messages", this::addMessage);
        app.put("/messages/{id}", this::updateMessageById);
        app.delete("/messages/{id}", this::deleteMessageById);

        return app;
    }

    // add dependency
    MessageService messageService;

    public MessageController() {
        this.messageService = new MessageService();
    }

    /**
     * This is an example handler for an example endpoint.
     *
     * @param context The Javalin Context object manages information about both the
     *                HTTP request and response.
     */
    private void exampleHandler(Context context) {
        // 1. get info
        // 2. call service
        // 3. process results
        context.json("sample text");
    }

    // create handlers
    private void getAllMessages(Context ctx) {
        ArrayList<Message> messagesRetrieved = messageService.getAllMessages();
        // ctx.json(messagesRetrieved).status(200);
        if (messagesRetrieved != null) {
            ctx.json(messagesRetrieved).status(200);
        } else {
            ctx.result("Could not get all messages").status(400);
        }
    }

    private void getAllUserMessages(Context ctx) {
        int userId_fromPath = Int.parseInt(ctx.pathParam("userId"));
        ArrayList<Message> messagesRetrieved = messageService.getAllUserMessages(userId_fromPath);
        // ctx.json(messagesRetrieved).status(200);
        if (messagesRetrieved != null) {
            ctx.json(messagesRetrieved).status(200);
        } else {
            ctx.result("Could not get user messages").status(400);
        }
    }

    private void getMessageById(Context ctx) {
        int id_fromPath = Int.parseInt(ctx.pathParam("id"));
        Message messageRetrieved = messageService.getMessageById(id_fromPath);
        if (messageRetrieved != null) {
            ctx.json(messageRetrieved).status(200);
        } else {
            ctx.result("Could not find message").status(400);
        }
    }

    private void addMessage(Context ctx) {
        Message messageFromBody = ctx.bodyAsClass(Message.class);
        Message messageInserted = messageService.addMessage(messageFromBody);
        if (messageInserted != null) {
            ctx.json(messageInserted).status(200);
        } else {
            ctx.result("Message could not be created").status(400);
        }
    }

    private void updateMessageById(Context ctx) {
        int id_fromPath = Int.parseInt(ctx.pathParam("id"));
        Message messageFromBody = ctx.bodyAsClass(Message.class);
        Message updatedMessage = messageService.updateMessageById(id_fromPath, messageFromBody);
        if (updatedMessage != null) {
            ctx.json(updatedMessage).status(200);
        } else {
            ctx.result("Message could not be updated").status(400);
        }
    }

    private void deleteMessageById(Context ctx) {
        int id_fromPath = Int.parseInt(ctx.pathParam("id"));
        boolean isDeleted = messageService.deleteMessageById(id_fromPath);
        if (isDeleted) {
            ctx.result("Message was deleted").status(200);
        } else {
            ctx.result("Message was not deleted or could not be found").status(400);
        }
    }

}



// -----------
public class MessageService {
    MessageDAO messageDAO;
    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    public Message updateMessageById(int message_id, Message messageFromBody) {
        boolean result = messageDAO.updateMessageById(message_id, messageFromBody.getMessage_text());
        if (result) {
            return messageDAO.getMessageById(message_id);
        } else {
            return null;
        }
    }

}
