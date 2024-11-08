package com.example.repository;
import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * Repository interface for performing CRUD operations, extending JpaRepository for custom query methods.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{
    /**
     * Deletes a message by a message ID.
     * 
     * @param messageId The ID of the message to delete.
     * @return The number of rows deleted, or 0 if no message is deleted.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Message m where m.id = ?1")
    int deleteMessageById (Integer messageId);

    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A list of mesages posted by the user.
     */
    @Query("SELECT m FROM Message m where m.postedBy = ?1")
    List<Message> findAllByPostedBy(Integer accountId);
}
