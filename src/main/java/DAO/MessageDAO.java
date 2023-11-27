package dao;

import java.sql.*;
import java.util.*;

import util.ConnectionUtil;
import model.Message;

// TODO: do i include long time_posted_epoch?
public class MessageDAO {

    // 1 --> read all messages
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> listReturned = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long timestamp = rs.getLong("time_posted_epoch");
                listReturned.add(new Message(id, posted_by, message_text, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listReturned;
    }

    // 2 --> read all messages for user
    public ArrayList<Message> getAllUserMessages(int posted_by) {
        ArrayList<Message> listReturned = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, posted_by);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt("message_id");
                int posted_by_x = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long timestamp = rs.getLong("time_posted_epoch");
                listReturned.add(new Message(id, posted_by_x, message_text, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listReturned;
    }

    // 3 --> read message by MessageId
    public Message getMessageById(int message_id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, message_id);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long timestamp = rs.getLong("time_posted_epoch");

                return new Message(id, posted_by, message_text, timestamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 4 --> create message
    public Message addMessage(int posted_by, String message_text) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, posted_by);
            ps.setString(2, message_text);
            Timestamp current = new Timestamp(System.currentTimeMillis());
            long timestamp = current.getTime();
            ps.setLong(3, timestamp);

            // 2. execute statement
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt(1);
                return new Message(id, message_text, posted_by, timestamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 5 --> update message text
    public boolean updateMessageById(int message_id, String message_text) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "UPDATE message SET message_text = ?, time_posted_epoch = ? WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, message_text);
            Timestamp current = new Timestamp(System.currentTimeMillis());
            long timestamp = current.getTime();
            ps.setLong(2, timestamp);
            ps.setInt(3, message_id);

            // 2. execute statement
            int result = ps.executeUpdate();

            // 3. process results
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 6 --> delete message by MessageId
    public boolean deleteMessageById(int message_id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, message_id);

            // 2. execute statement
            int result = ps.executeUpdate();

            // 3. process results
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
