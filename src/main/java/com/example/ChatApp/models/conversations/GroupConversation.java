package com.example.ChatApp.models.conversations;

import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("GROUP")
public class GroupConversation extends BaseConversation {
    private String conversationName;

    @ManyToMany
    @JoinTable(
            name = "conversations_admins",
            joinColumns = {@JoinColumn(name = "conversation_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)}
    )
    private Set<User> admins = new HashSet<>();

    public GroupConversation(Set<User> admins, Set<User> members, String conversationName) {
        super(members, ConversationType.GROUP);
        this.conversationName = conversationName;
        this.admins = admins;
    }
}
