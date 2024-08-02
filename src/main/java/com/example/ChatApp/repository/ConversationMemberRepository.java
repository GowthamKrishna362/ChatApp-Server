package com.example.ChatApp.repository;

import com.example.ChatApp.Models.Entity.Conversation;
import com.example.ChatApp.Models.Entity.ConversationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, Long> {
    @Query("SELECT cm.conversation FROM ConversationMember cm WHERE cm.user.username = :username")
    List<Conversation> findConversationsByUsername(@Param("username") String username);
}
