package com.example.repository;
import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;



public interface MessageRepository extends JpaRepository<Message, Integer>
{
    @Transactional
    @Modifying
    @Query("DELETE FROM Message m where m.id = ?1")
    int deleteMessageById (Integer messageId);

    @Query("SELECT m FROM Message m where m.postedBy = ?1")
    List<Message> findAllByPostedBy(Integer accountId);

}
