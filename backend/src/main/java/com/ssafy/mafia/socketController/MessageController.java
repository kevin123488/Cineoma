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
//	@MessageMapping("/receiveChat/{roomNo}")
//	@SendTo("/sendChat")
//	public ChatDto socketTest(@DestinationVariable int roomNo, ChatDto chatDto)
////	public ChatDto socketTest( ChatDto chatDto)
//	{
//		System.out.println("roomNo : "+ roomNo);
//		System.out.println("NickName : "+ chatDto.getNickName());		
//		System.out.println("Content : "+ chatDto.getContent());
//		return chatDto;
//		
//	}
	@MessageMapping("/receiveChat")
    public ChatDto chat(ChatDto chatDto) {
//		public ChatDto chat(@DestinationVariable int roomNo, ChatDto chatDto) {
			
		System.out.println("roomNo : "+ chatDto.getRoomNo());
		System.out.println("NickName : "+ chatDto.getNickName());		
		System.out.println("Content : "+ chatDto.getContent());
		sendingOperations.convertAndSend("/sendChat/"+chatDto.getRoomNo(), chatDto);
        return chatDto;
    }
	
//	@MessageMapping("/receiveProfile")
//    public ChatDto profile(ChatDto chatDto) {
////		public ChatDto chat(@DestinationVariable int roomNo, ChatDto chatDto) {
//			
//		System.out.println("roomNo : "+ chatDto.getRoomNo());
//		System.out.println("NickName : "+ chatDto.getNickName());		
//		System.out.println("Content : "+ chatDto.getContent());
//		sendingOperations.convertAndSend("/sendProfile/"+chatDto.getRoomNo(), chatDto);
//        return chatDto;
//    }
}
