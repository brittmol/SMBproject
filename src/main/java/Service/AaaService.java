package service;

import java.util.ArrayList;
import dao.NoteDAO;
import model.Note;

public class NoteService {

  // add dependency
  private NoteDAO noteDAO;

  public NoteService() {
    this.noteDAO = new NoteDAO();
  }

  public ArrayList<Note> getAllNotes() {
    return noteDAO.getAllNotes();
  }

  public Note getNoteById(long id) {
    return noteDAO.getNoteById(id);
  }

  public Note addNote(Note noteToAdd) {
    return noteDAO.addNote(noteToAdd.getContent(), noteToAdd.getPriority());
  }

  public Note updateNoteById(long id, Note noteToUpdate) {
    boolean result = noteDAO.updateNoteById(id, noteToUpdate.getContent(), noteToUpdate.getPriority());

    if (result) {
      return noteDAO.getNoteById(id);
    } else {
      return null;
    }
  }

  public Note updateNotePriorityById(long id, String priority) {
    boolean result = noteDAO.updateNotePriorityById(id, priority);

    if (result) {
      return noteDAO.getNoteById(id);
    } else {
      return null;
    }
  }

  // public Note deleteNoteById(long id) {
  //   return noteDAO.deleteNoteById(id);
  // }

}
