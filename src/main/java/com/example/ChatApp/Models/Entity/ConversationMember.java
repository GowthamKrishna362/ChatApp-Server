package com.example.ChatApp.Models.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name = "ConversationMembers")
public class ConversationMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "conversationId", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    public ConversationMember(String username, Long conversationId) {
        this.user = new User(username);
        this.conversation = new Conversation(conversationId);
    }
}
