package com.example.ChatApp.models;

import com.example.ChatApp.models.conversations.BaseConversation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_group_open_events")
public class ConversationOpenEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private BaseConversation conversation;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime lastOpenedAt;

    public ConversationOpenEvent(User user, BaseConversation conversation, LocalDateTime openedAt) {
        this.conversation = conversation;
        this.user = user;
        this.lastOpenedAt = openedAt;
    }
}
