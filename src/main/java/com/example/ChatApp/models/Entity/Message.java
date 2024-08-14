package com.example.ChatApp.Models.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private Conversation conversation;

    private String messageContent;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    public Message() {

    }

    public Message(String messageContent, String sender, Long conversationId, LocalDateTime timestamp) {
        this.messageContent = messageContent;
        this.user = new User(sender);
        this.conversation = new Conversation(conversationId);
        this.timestamp = timestamp;
    }
}
