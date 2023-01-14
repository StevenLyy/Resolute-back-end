package com.ly.Resolute.resource;

import com.ly.Resolute.resource.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatResource {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        System.out.println("testPublic");
        return message;
    }

    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println("testPrivate");
        return message;
    }

}
