package com.example.ChatApp.models;

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
@DiscriminatorColumn(name = "CONVERSATION_TYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Conversations")
public abstract class BaseConversation {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONVERSATION_TYPE", insertable = false, updatable = false)
    private ConversationType conversationType;

    @ManyToMany
    @JoinTable(
            name = "Conversations_Users",
            joinColumns = {@JoinColumn(name = "conversation_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)}
    )

    private Set<User> members = new HashSet<>();

    public BaseConversation(Set<User> members, ConversationType conversationType) {
        this.members = members;
        this.conversationType = conversationType;
    }

}
