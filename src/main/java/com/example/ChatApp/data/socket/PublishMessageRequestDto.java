package com.example.ChatApp.data.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublishMessageRequestDto extends SocketDtoBase {
    private  UUID tempId;
    private  String sender;
    private  String messageContent;
    private  Long conversationId;
}
