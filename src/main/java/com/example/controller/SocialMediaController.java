package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.MessageNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import java.util.List;

/**
 * Controller class to manage accounts and messages.
 */
@RestController
public class SocialMediaController 
{
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRepository messageRepository;


    /**
     * Registers a new user account.
     * 
     * @param account The account to register.
     * @return ResponseEntity with status 200 if the registration is successful
     *         or status 409 (CONFLICT) if the account already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account)
    {
        Account existedAccount = accountService.getAccountByUsername(account.getUsername());
        if(existedAccount != null)
        {
            return ResponseEntity.status(409).build(); // 409 - CONFLICT
        }
        Account newAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(newAccount);

    }

    /**
     * Logs in a user by authenticating their credentials
     * 
     * @param account The account to authenticate.
     * @return ResponseEntity with status 200 if the authentication is successful
     *         or status 401 (UNAUTHORIZED) if the authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account)
    {
        // Authenticate the account
        Account loginAccount = accountService.authenticate(account);
        if(loginAccount == null)
        {
            return ResponseEntity.status(401).build(); // 401 - UNAUTHORIZED
        }
        return ResponseEntity.ok(loginAccount);
    }
    
    /**
     * Creates a new message.
     * 
     * @param message The message to create.
     * @return ResponseEntity with status 200 if the creation of message is successful
     *         or status 400 (BAD_REQUEST) if the message is invalid.
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message)
    {
        if(message.getMessageText().trim().isEmpty() || message.getMessageText().length() > 255)
        {
            return ResponseEntity.badRequest().build();
        }

        if(!accountRepository.existsById(message.getPostedBy()))
        {
            return ResponseEntity.badRequest().build();
        }

        Message newMessage = messageService.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }
    
    /**
     * Retrieves all messages.
     * 
     * @return ResponseEntity with status 200 and a list of messages
     *         or status 200 with no content if there is no message.
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages()
    {
        List<Message> messages = messageService.getAllMessages();
        if(messages.isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(messages);
    }

     /**
     * Retrieves a message by a message ID.
     * 
     * @param messageId The ID of the message to retrieve.
     * @return ResponseEntity with status 200 and the message if found
     *         or status 200 with no content if the message is not found.
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId)
    {
        Message message = messageService.getMessageById(messageId);
        if(message == null)
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(message);
    }

    /**
     * Deletes a message by a message ID.
     * 
     * @param messageId The ID of the message to delete.
     * @return ResponseEntity with status 200 and the number of rows deleted
     *         or status 200 with no content if no message is deleted.
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId)
    {
        int rowsDeleted = messageService.deleteMessageById(messageId);
        if(rowsDeleted == 0)   
        {
            return ResponseEntity.ok().build();
            
        }
        return ResponseEntity.ok(rowsDeleted);
    }

    /**
     * Updates an existing message by a message ID.
     * 
     * @param messageId The ID of the message to update.
     * @return ResponseEntity with status 200 if the update of the message is successful
     *         or status 400 (BAD_REQUEST) if the message is invalid or not found.
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message)
    {
        if(message.getMessageText().trim().isEmpty() || message.getMessageText().length() > 255)
        {
            return ResponseEntity.badRequest().body("Message is either blank or exceeds 255 characters.");
        }
        Message newMessage = messageService.updateMessageById(messageId, message);

        if(newMessage == null)
        {
            return ResponseEntity.badRequest().body("Message with ID (" + messageId + ") is not found.");
        }
        return ResponseEntity.ok(1);
    }

    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return ResponseEntity with status 200 and a list of messages for the user
     *         or status 200 with no content if there is no message.
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser(@PathVariable Integer accountId)
    {
        List<Message> messages = messageService.getAllMessagesByUser(accountId);
        return ResponseEntity.ok(messages);
    }
}
