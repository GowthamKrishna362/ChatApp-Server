package com.example.ChatApp.repository;
import com.example.ChatApp.models.ConversationOpenEvent;
import com.example.ChatApp.models.User;
import com.example.ChatApp.models.conversations.BaseConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConversationOpenEventRepository extends  JpaRepository<ConversationOpenEvent, Long> {
    Optional<ConversationOpenEvent> findByConversationAndUser(BaseConversation conversation, User user);
}
