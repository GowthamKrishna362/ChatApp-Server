package com.example.ChatApp.Models.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="Messages")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String messageContent;
    private String sender;
    private Long conversationId;

    public Message(String messageContent, String sender, Long conversationId) {
        this.messageContent = messageContent;
        this.sender = sender;
        this.conversationId = conversationId;
    }
}
