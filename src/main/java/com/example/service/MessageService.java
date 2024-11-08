package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class to handle operations related to messages. 
 */
@Service
public class MessageService 
{
    @Autowired
    private MessageRepository messageRepository;

    /**
     * Creates a new message and saves it to the repository.
     * 
     * @param message The message to save.
     * @return The saved message.
     */
    public Message createMessage(Message message) 
    {
        return messageRepository.save(message);
    }

    /**
     * Retrieves all messages from the repository.
     * 
     * @return a list of all messages.
     */
    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    /**
     * Retrieves a message by a message ID.
     * @param messageId The ID of the message to retrieve.
     * @return The retrieved message if found, or null if not found.
     */
    public Message getMessageById(Integer messageId) 
    {
       Optional<Message> message = messageRepository.findById(messageId);
       return message.orElse(null);
    }

    /**
     * Deletes a message by a message ID.
     * @param messageId The ID of the message to delete.
     * @return The number of rows deleted, or 0 if no message is deleted.
     */
    public int deleteMessageById(Integer messageId) 
    {
        int rowsDeleted = 0;
        if(messageRepository.existsById(messageId))
        {
            rowsDeleted = messageRepository.deleteMessageById(messageId);
        }
        return rowsDeleted;
    }

    /**
     * Updates an existing message by a message ID.
     * @param messageId The ID of the message to update.
     * @param message The new message 
     * @return The updated message if the update is successful, or null if the message does not exist.
     * @throws IllegalArgumentException If the message is invalid.
     */
    public Message updateMessageById(Integer messageId, Message message) 
    {
        if(messageRepository.existsById(messageId))
        {
            if(message.getMessageText().trim().isEmpty())
            {
                throw new IllegalArgumentException("Message text cannot be empty.");
            }
            if(message.getMessageText().length() > 255)
            {
                throw new IllegalArgumentException("Message text cannot exceed 255 characters.");
            }
            return messageRepository.save(message);
        }
        return null;
    }

    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A list of messages posted by the user.
     */
	public List<Message> getAllMessagesByUser(Integer accountId) 
    {
        return messageRepository.findAllByPostedBy(accountId);
	}
}