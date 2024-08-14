package com.example.ChatApp.models.Entity;

import com.example.ChatApp.data.enums.ConversationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("PRIVATE")
@Table(name="Conversations")
public class PrivateConversation extends BaseConversation {

    public PrivateConversation(Set<User> members) {
        super(members, ConversationType.PRIVATE);
    }
}
