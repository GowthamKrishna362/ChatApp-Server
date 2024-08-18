package com.example.ChatApp.data.conversation.response;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.models.conversations.BaseConversation;
import com.example.ChatApp.models.conversations.GroupConversation;
import com.example.ChatApp.models.Message;
import com.example.ChatApp.models.conversations.PrivateConversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConversationMessageDetailsDto {
    private List<MessageResponseDto> messages;
    private List<ConversationOpenEventDto> conversationLastOpenedList;

}