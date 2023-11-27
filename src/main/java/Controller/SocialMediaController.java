package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;

import model.Message;
import service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
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
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        // Create, 

        return app;
    }

    // add dependency
    MessageService messageService;
    public MessageController() {
        this.messageService = new MessageService();
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}
