package DAO;

import java.sql.*;
import java.util.*;

import Util.ConnectionUtil;
import Model.Account;

public class AccountDAO {
    // User Registration
    public Account createAccount(Account createdAccount) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, createdAccount.getUsername());
            ps.setString(2, createdAccount.getPassword());

            // 2. execute statement
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt(1);
                return new Account(id, createdAccount.getUsername(), createdAccount.getPassword());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // User Login
    public Account getAccount(String username, String password) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while (rs.next()) {
                int id = rs.getInt("account_id");
                return new Account(id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // get all accounts
    public ArrayList<String> getAllUsernames() {
        ArrayList<String> listReturned = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT username FROM account";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while (rs.next()) {
                String username = rs.getString("username");
                listReturned.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listReturned;
    }

}
