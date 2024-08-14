package com.example.ChatApp.repository;

import com.example.ChatApp.Models.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
