package Service;

import java.util.*;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    // add dependency
    AccountDAO accountDAO;
    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    public Account getAccount(String username, String password) {
        return accountDAO.getAccount(username, password);
    }

    // add other stuff
    public Account createAccount(Account newAccount) {
        // make an array list of all accounts
        ArrayList<String> allAccounts = new ArrayList<>(accountDAO.getAllUsernames());
        boolean usernameExists = allAccounts.contains(newAccount.getUsername());
        if(newAccount.getUsername().length() > 0 && newAccount.getPassword().length() >= 4 && !usernameExists) {
            return accountDAO.createAccount(newAccount);
        }
        return null;
    }

}
