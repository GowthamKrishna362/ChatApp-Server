package com.example.ChatApp.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@DiscriminatorValue("GROUP")
public class GroupConversation extends BaseConversation{
    @ManyToMany
    @JoinTable(
            name = "conversations_admins",
            joinColumns = {@JoinColumn(name = "conversation_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)}
    )
    private Set<User> admins;

}
