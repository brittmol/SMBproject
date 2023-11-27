package controller;

import java.util.ArrayList;

import io.javalin.Javalin;
import io.javalin.http.Context;

import model.Note;
import service.NoteService;

public class NoteController {

  // add dependency
  private NoteService noteService;

  public NoteController() {
    this.noteService = new NoteService();
  }

  public void setup() {
    // create Javalin object
    Javalin app = Javalin.create().start(8080);

    // create endpoints
    app.get("/", ctx -> {
      System.out.println("Sanity test! Hello there!");
      ctx.result("hello world!");
    });

    app.get("/notes", this::getAllNotes);
    app.get("/notes/{id}", this::getNoteById);
    app.post("/notes", this::addNote);
    app.put("/notes/{id}", this::updateNoteById);
    app.patch("/notes/{id}", this::updateNotePriorityById);
    // app.delete("/notes/{id}", this::deleteNoteById);

  }

  // create handlers
  private void getAllNotes(Context ctx) {
    ArrayList<Note> notesRetrieved = noteService.getAllNotes();
    ctx.json(notesRetrieved).status(200);
  }

  private void getNoteById(Context ctx) {
    // get info
    long idFromPath = Long.parseLong(ctx.pathParam("id"));

    // call service
    Note noteReceived = noteService.getNoteById(idFromPath);

    // process results
    if (noteReceived != null) {
      ctx.json(noteReceived).status(200);
    } else {
      ctx.result("Note was not received!").status(400);
    }
  }

  private void addNote(Context ctx) {
    Note noteFromBody = ctx.bodyAsClass(Note.class);
    Note noteInserted = noteService.addNote(noteFromBody);
    if (noteInserted != null) {
      ctx.json(noteInserted).status(200);
    } else {
      ctx.result("Note was not inserted!").status(400);
    }
  }

  private void updateNoteById(Context ctx) {
    // in model > Note.java, you can see that the id is "long"
    // pathParam comes back as String, so you have to convert it
    // here, we are using a wrapper class
    long idFromPath = Long.parseLong(ctx.pathParam("id"));
    Note noteFromBody = ctx.bodyAsClass(Note.class);
    Note updatedNote = noteService.updateNoteById(idFromPath, noteFromBody);
    if (updatedNote != null) {
      ctx.json(updatedNote).status(200);
    } else {
      ctx.result("Note was not updated!").status(400);
    }
  }

  private void updateNotePriorityById(Context ctx) {
    long idFromPath = Long.parseLong(ctx.pathParam("id"));
    String queryParamValue = ctx.queryParam("priority");
    Note updatedNote = noteService.updateNotePriorityById(idFromPath, queryParamValue);
    if (updatedNote != null) {
      ctx.json(updatedNote).status(200);
    } else {
      ctx.result("Note was not updated!").status(400);
    }
  }

  private void deleteNoteById(Context ctx) {
    long idFromPath = Long.parseLong(ctx.pathParam("id"));
    boolean deletionSuccessful = noteService.deleteNoteById(idFromPath);
    if(deletionSuccessful) {
      ctx.result("Note was deleted!").status(200);
    } else {
      ctx.result("Note was not deleted or could not be found!").status(400);
    }
  }

}
