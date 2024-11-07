package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService
{
    @Autowired
    private AccountRepository accountRepository;

    
    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }


    
    // public Account registerAccount(Account account)
    // {
    //     // Validate the account
    //     validateAccount(account); 

    //     // Check if account already exists by username
    //     Account searchedAccount = getAccountByUsername(account.getUsername());
    //     if(searchedAccount != null)
    //     {
    //         throw new RuntimeException("Account already exists.");
    //     }

    //     // Insert new account into the database
    //     Account newAccount = accountDao.insertAccount(account);
    //     return newAccount;
    // }
    
    
    // public Account authenticate(Account account)
    // {
    //     String username = account.getUsername().trim();
    //     String password = account.getPassword().trim();

    //     Account retrievedAccount = getAccountByUsername(username);
    //     if(retrievedAccount != null && retrievedAccount.getPassword().equals(password))
    //     {
    //         return retrievedAccount;
    //     }
    //     return null;
    // }
    
    
    // private Account getAccountByUsername(String username)
    // {
    //     return accountRepository.findByUsername(username);   
    // }
   
    
    // private void validateAccount(Account account)
    // {
    //     String username = account.getUsername();
    //     String password = account.getPassword();
    //     boolean usernameExisted = false;

    //     // Check if username is empty
    //     if(username.isEmpty())
    //     {
    //         throw new IllegalArgumentException("Username cannot be empty.");
    //     }

    //     // Check if password is empty
    //     if(password.isEmpty())    
    //     {
    //         throw new IllegalArgumentException("Password cannot be empty.");
    //     }

    //     // Check if password is at least 4 characters
    //     if(password.length() < 4)
    //     {
    //         throw new IllegalArgumentException("Password must be at least 4 characters.");
    //     }
        
    //     // Check if username already exists
    //     usernameExisted = accountRepository.existsById(null).alreadyExist(account.getUsername());
    //     if(usernameExisted)
    //     {
    //         throw new RuntimeException("Username is already existed.");
    //     }
    // }
  

    // public Account getAccountByAccountId(int posted_by)
    // {
    //     Account account = accountDao.getAccountByAccountId(posted_by);    
    //     return account;
    // }
}