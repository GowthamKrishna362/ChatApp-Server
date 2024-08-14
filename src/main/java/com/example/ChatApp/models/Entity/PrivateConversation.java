package com.example.ChatApp.models.Entity;

import com.example.ChatApp.data.enums.ConversationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Conversations")
public class Conversation {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String conversationName;
    private ConversationType conversationType;
    private String lastMessage;

    @ManyToMany
    @JoinTable(
            name = "Conversations_Users",
            joinColumns = {@JoinColumn(name = "conversation_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)}
    )
    private Set<User> members = new HashSet<>();

    public Conversation(Set<User> members, ConversationType conversationType) {
        this.members = members;
        this.conversationType = conversationType;
    }

}
