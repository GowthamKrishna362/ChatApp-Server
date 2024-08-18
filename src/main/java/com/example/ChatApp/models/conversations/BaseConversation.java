package com.example.ChatApp.models.conversations;

import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.models.BaseEntity;
import com.example.ChatApp.models.ConversationOpenEvent;
import com.example.ChatApp.models.Message;
import com.example.ChatApp.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONVERSATION_TYPE")
@Table(name="Conversations")
public abstract class  BaseConversation extends BaseEntity {
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

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    @OneToMany(mappedBy = "conversation")
    private List<ConversationOpenEvent> conversationOpenEvents;

    public BaseConversation(Set<User> members, ConversationType conversationType) {
        this.members = members;
        this.conversationType = conversationType;
    }

}
