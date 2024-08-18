package com.example.ChatApp.models.conversations;

import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("PRIVATE")
public class PrivateConversation extends BaseConversation {

    public PrivateConversation(Set<User> members) {
        super(members, ConversationType.PRIVATE);
    }
}
