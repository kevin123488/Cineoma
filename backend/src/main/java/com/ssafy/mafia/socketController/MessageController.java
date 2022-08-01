package com.ssafy.mafia.socketController;

import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.mafia.dto.ChatDto;
import com.ssafy.mafia.dto.MafiaParamDto;

@Controller
public class MessageController {
	@MessageMapping("/receiveChat/{roomNo}")
	@SendTo("/sendChat")
	public ChatDto socketTest(@DestinationVariable int roomNo, ChatDto chatDto)
//	public ChatDto socketTest(@PathVariable int roomNo, ChatDto chatDto)
	{
		System.out.println("roomNo : "+ roomNo);
		System.out.println("NickName : "+ chatDto.getNickName());		
		System.out.println("Content : "+ chatDto.getContent());
		return chatDto;
		
	}
}
