package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a message is not found.
 * 
 * When this exception is thrown, the HTTP response status is set to 400 - BAD_REQUEST.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MessageNotFoundException extends RuntimeException 
{
    /**
     * Constructs a new MessageNotFoundException with a message detail
     * @param message The detail of the message.
     */
    public MessageNotFoundException(String message) 
    {
        super(message);
    }
}