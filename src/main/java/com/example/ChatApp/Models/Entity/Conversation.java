package com.example.ChatApp.Models.Entity;

import com.example.ChatApp.Models.Enum.ConversationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conversationName;
    private ConversationType conversationType;
    private String lastMessage;

    public Conversation(String conversationName) {
        this.conversationName = conversationName;
        this.conversationType = ConversationType.PRIVATE;
    }

    public Conversation(Long conversationId) {
        this.id = conversationId;
    }

}
