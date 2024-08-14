package com.example.ChatApp.repository;

import com.example.ChatApp.models.Entity.BaseConversation;
import com.example.ChatApp.models.Entity.PrivateConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<BaseConversation, Long> {

    Optional<BaseConversation> findById(UUID conversationId);
}
