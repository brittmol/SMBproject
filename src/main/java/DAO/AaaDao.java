package dao;

import java.sql.*;
import java.util.ArrayList;

import util.ConnectionUtil;
import model.Note;

public class NoteDAO {

  // create
  public Note addNote(String content, String priority) {

    try (Connection connection = ConnectionUtil.getConnection()) {
      // step 2: create your statement
      PreparedStatement ps =
        connection.prepareStatement("INSERT INTO notes(content, priority) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

      // assign any parameters their values
      ps.setString(1, content);
      ps.setString(2, priority);

      // step 3: execute statement
      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();

      // step 4: process results:
        // while there is another record in the resultset to process...
      while (rs.next()) {
        // ... get the value of the first column in that resultset...
        long resultId = rs.getLong(1);

        // ... and return a Note with the generated id in its state, as well as the other values
        return new Note(resultId, content, priority);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;

  }

  // read
  public Note getNoteById(long id) {

    // step 1 & 5: open connection to db and close when done (ignore red squiggles, replit is confused)
    try (Connection connection = ConnectionUtil.getConnection()){
      // step 2: create your statement
      PreparedStatement ps =
        connection.prepareStatement("SELECT * FROM notes WHERE id = ?");

      // assign any parameters their values
      ps.setLong(1, id);

      // step 3: execute statement
      ResultSet rs = ps.executeQuery();

      // step 4: process results:
        // while there is another record in the resultset to process...
      while (rs.next()) {
        // ... get the values from the respective columns ...
        long resultId = rs.getLong("id");
        String content = rs.getString("content");
        String priority = rs.getString("priority");

        // ... and return a Note with those values as its state
        return new Note(resultId, content, priority);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    // return null if unsuccessful
    return null;

  }

  public ArrayList<Note> getAllNotes() {
  // create list to return
    ArrayList<Note> listReturned = new ArrayList<>();

    // step 1 & 5: connection to database, close connection when done (ignore red squiggles, replit is confused)
    try (Connection connection = ConnectionUtil.getConnection()){
      // step 2: create your pre-compiled statement
      PreparedStatement ps =
        connection.prepareStatement("SELECT * FROM notes");

      // step 3: execute statement
      ResultSet rs = ps.executeQuery();

      // step 4: process results
      while (rs.next()) {
        long resultId = rs.getLong("id");
        String content = rs.getString("content");
        String priority = rs.getString("priority");
        listReturned.add(new Note(resultId, content, priority));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return listReturned;

  }

  // update
  public boolean updateNoteById(long id, String content, String priority) {

    try (Connection connection = ConnectionUtil.getConnection()) {
      // step 2: create your statement
      PreparedStatement ps =
        connection.prepareStatement("UPDATE notes SET content = ?, priority = ? WHERE id = ?");

      // assign any parameters their values
      ps.setString(1, content);
      ps.setString(2, priority);
      ps.setLong(3, id);

      // step 3: execute statement
      int result = ps.executeUpdate();

      // executeUpdate() returns a number greater than zero representing the number of rows affected
      // IF a record was affected...
      if (result == 1) {
        // ... return true
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }

  public boolean updateNotePriorityById(long id, String priority) {

    try (Connection connection = ConnectionUtil.getConnection()) {
      // step 2: create your statement
      PreparedStatement ps =
        connection.prepareStatement("UPDATE notes SET priority = ? WHERE id = ?");

      // assign any parameters their values
      ps.setString(1, priority);
      ps.setLong(2, id);

      // step 3: execute statement
      int result = ps.executeUpdate();

      // executeUpdate() returns a number greater than zero representing the number of rows affected
      // IF a record was affected...
      if (result == 1) {
        // ... return true
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }

  // delete
  public boolean deleteNoteById(long id) {

    try (Connection connection = ConnectionUtil.getConnection()) {
      // step 2: create your statement
      PreparedStatement ps =
        connection.prepareStatement("DELETE FROM notes WHERE id = ?");

      // assign any parameters their values
      ps.setLong(1, id);

      // step 3: execute statement
      int result = ps.executeUpdate();

      // IF a record was affected...
      if (result == 1) {
        // ... return true
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }


}



