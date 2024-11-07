package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.MessageNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

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

    // register
    // login
    // create message
    // get all message
    // get message by id
    // delete message by id
    // update message by id
    // get message by user
    
    // ###3
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
    // ###4
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

    // ###5
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

    // ###6
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


    // ###7
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

    // ###8
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser(@PathVariable Integer accountId)
    {
        List<Message> messages = messageService.getAllMessagesByUser(accountId);
        return ResponseEntity.ok(messages);
    }
}
