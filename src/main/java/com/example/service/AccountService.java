package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class to handle operations related to user accounts. 
 */
@Service
public class AccountService
{
    @Autowired
    private AccountRepository accountRepository;
    
    /**
     * Retrieves all accounts from the repository.
     * 
     * @return A list of all accounts.
     */
    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }

    /**
     * Retrieves an account by username.
     * 
     * @param username The username of the account to retrieve.
     * @return The account given by the username, or null if not found.
     */
    public Account getAccountByUsername(String username) 
    {
        return accountRepository.findByUsername(username);
    }

    /**
     * Saves an account to the repository.
     * 
     * @param account The account to save.
     * @return The saved account.
     */
    public Account saveAccount(Account account) 
    {
        return accountRepository.save(account);
    }

    /**
     * Authenticates an account based on username and password.
     * 
     * @param account The account to authenticate.
     * @return The account if successfully authenticated, or null if username or password is incorrect.
     */
    public Account authenticate(Account account) 
    {
        String username = account.getUsername();
        String password = account.getPassword();

        Account retrievedAccount = getAccountByUsername(username);
        if(retrievedAccount != null && retrievedAccount.getPassword().equals(password))
        {
            return retrievedAccount;
        }
        return null;
    }
}