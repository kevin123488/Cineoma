package com.ssafy.mafia.socketController;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.ssafy.mafia.dto.ChatDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	 private final SimpMessageSendingOperations sendingOperations;

	@MessageMapping("/receiveChat")
    public void chat(ChatDto chatDto) {
		System.out.println("roomNo : "+ chatDto.getRoomNo());
		System.out.println("NickName : "+ chatDto.getNickName());		
		System.out.println("Content : "+ chatDto.getContent());
		//front에서 구독받을 uri로 보내줌
		sendingOperations.convertAndSend("/topic/sendChat/"+chatDto.getRoomNo(), chatDto);
        return;
    }
}
