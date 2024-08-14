package com.example.ChatApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private BaseConversation conversation;

    private String messageContent;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    public Message() {

    }

    public Message(String messageContent, User sender, BaseConversation conversation, LocalDateTime timestamp) {
        this.messageContent = messageContent;
        this.sender = sender;
        this.conversation = conversation;
        this.timestamp = timestamp;
    }
}
