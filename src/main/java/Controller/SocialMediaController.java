package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

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

    // add dependency
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        // Home page
        app.get("/", ctx -> {
            System.out.println("Home Page here :) ");
            ctx.result("Home Page here");
        });

        // POST register and login
        app.post("/register", this::register);
        app.post("/login", this::login);

        // GET all, GET all by user, GET by id
        app.get("/messages", this::getAllMessages);
        app.get("/accounts/{accountId}/messages", this::getAllUserMessages);
        app.get("/messages/{id}", this::getMessageById);

        // POST, PATCH, DELETE
        app.post("/messages", this::addMessage);
        app.patch("/messages/{id}", this::updateMessageById);
        app.delete("/messages/{id}", this::deleteMessageById);

        return app;
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

    private void register(Context ctx) {
        Account accountFromBody = ctx.bodyAsClass(Account.class);
        Account accountInserted = accountService.createAccount(accountFromBody);
        if (accountInserted != null) {
            ctx.json(accountInserted).status(200);
        } else {
            ctx.result("").status(400);
        }
    }

    private void login(Context ctx) {
        Account accountFromBody = ctx.bodyAsClass(Account.class);
        // need to make sure the accountFromBody username and password
        // is the same as the getAccount
    }

    private void getAllMessages(Context ctx) {
        ArrayList<Message> messagesRetrieved = messageService.getAllMessages();
        // ctx.json(messagesRetrieved).status(200);
        if (messagesRetrieved != null) {
            ctx.json(messagesRetrieved).status(200);
        }
    }

    private void getAllUserMessages(Context ctx) {
        int accountId_fromPath = Integer.parseInt(ctx.pathParam("accountId"));
        ArrayList<Message> messagesRetrieved = messageService.getAllUserMessages(accountId_fromPath);
        // ctx.json(messagesRetrieved).status(200);
        if (messagesRetrieved != null) {
            ctx.json(messagesRetrieved).status(200);
        }
    }

    private void getMessageById(Context ctx) {
        int id_fromPath = Integer.parseInt(ctx.pathParam("id"));
        Message messageRetrieved = messageService.getMessageById(id_fromPath);
        if (messageRetrieved != null) {
            ctx.json(messageRetrieved).status(200);
        }
        // else {
        //     ctx.result("Could not find message").status(400);
        // }
    }

    private void addMessage(Context ctx) {
        Message messageFromBody = ctx.bodyAsClass(Message.class);
        Message messageInserted = messageService.addMessage(messageFromBody);
        if (messageInserted != null) {
            ctx.json(messageInserted).status(200);
        } else {
            ctx.result("").status(400);
        }
    }

    private void updateMessageById(Context ctx) {
        int id_fromPath = Integer.parseInt(ctx.pathParam("id"));
        Message messageFromBody = ctx.bodyAsClass(Message.class);
        Message updatedMessage = messageService.updateMessageById(id_fromPath, messageFromBody);
        if (updatedMessage != null) {
            ctx.json(updatedMessage).status(200);
        } else {
            ctx.result("").status(400);
        }
    }

    private void deleteMessageById(Context ctx) {
        int id_fromPath = Integer.parseInt(ctx.pathParam("id"));
        Message deletedMessage = messageService.deleteMessageById(id_fromPath);
        if (deletedMessage != null) {
            ctx.json(deletedMessage).status(200);
        } else {
            ctx.result("").status(200);
        }
    }

}
