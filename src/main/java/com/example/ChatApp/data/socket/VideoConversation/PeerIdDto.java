package com.example.ChatApp.data.socket.VideoConversation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PeerIdDto extends VideoCallDto{
    private String peerId;
}
