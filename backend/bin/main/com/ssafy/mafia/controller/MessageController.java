package com.ssafy.mafia.controller;

import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.ssafy.mafia.dto.ChatDto;
import com.ssafy.mafia.dto.MafiaParamDto;

public class MessageController {
	@MessageMapping("/receiveChat/{roomNo}")
	@SendTo("/sendChat")
	public ChatDto socketTest(@PageableDefault int roomNo, ChatDto chatDto)
	{
		System.out.println("roomNo : "+ roomNo);
		System.out.println("NickName : "+ chatDto.getNickName());		
		System.out.println("Content : "+ chatDto.getContent());
		return chatDto;
		
	}
}
