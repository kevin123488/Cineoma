package com.ssafy.mafia.socketController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.ssafy.mafia.dto.ReadyResultDto;
import com.ssafy.mafia.dto.RoomBreakDto;
import com.ssafy.mafia.service.RoomUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//레디 상태가 변화 할 때마다 방 전체에 알려주는 용도 및 게임 시작 사인을 보냄
public class RoomBreakController {
	 private final SimpMessageSendingOperations sendingOperations;
	 @Autowired
	 private RoomUserService roomUserService;
	@MessageMapping("/receiveBreak")
    public void chat(RoomBreakDto dto) throws Exception {
		//룸에 있는 모든 유저 db 삭제
		roomUserService.deleteRoomAllUser(dto.getRoomNo());
		
		//front에서 구독받을 uri로 보내줌
		sendingOperations.convertAndSend("/topic/receiveBreak/"+dto.getRoomNo(), dto);
        return;
    }
}
