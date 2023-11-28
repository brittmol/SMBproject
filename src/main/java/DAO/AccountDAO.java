/*
 *
 * Login:
    - successful
    - invalid username
    - invalid password

Register:
    - successful
    - duplicate username
    - username blank
    - user password length less than 4
 *
 */

package DAO;

import java.sql.*;
import java.util.*;

import Util.ConnectionUtil;
import Model.Account;

public class AccountDAO {
    // User Registration
    public Account addAccount(Account createdAccount) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql. Statement.RETURN_GENERATED_KEYS);

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
    public Account getAccount(int account_id) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            // 1. create statement & assign any parameters
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account_id);

            // 2. execute statement
            ResultSet rs = ps.executeQuery();

            // 3. process results
            while(rs.next()) {
                int id = rs.getInt("account_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                return new Account(id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
