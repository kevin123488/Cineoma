package com.ssafy.mafia.socketService;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.socketDto.RoomBreakDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomBreakServiceImpl implements RoomBreakService{

	private final SimpMessageSendingOperations sendingOperations;
	
	@Override
	public void sendBreakMsg(int roomNo) throws Exception {
		RoomBreakDto result = new RoomBreakDto(roomNo);
		sendingOperations.convertAndSend("/topic/sendBreak/"+roomNo, result);
	}

}
