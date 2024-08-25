package com.example.ChatApp.data.conversation.profile;

import com.example.ChatApp.data.user.UserProfile;
import com.example.ChatApp.models.conversations.GroupConversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupConversationProfile extends BaseConversationProfile {
    private String conversationName;
    private List<UserProfile> admins;
    public GroupConversationProfile(GroupConversation groupConversation) {
        super(groupConversation);
        this.admins = groupConversation.getAdmins().stream().map(UserProfile::new).toList();
        this.conversationName = groupConversation.getConversationName();
    }
}
