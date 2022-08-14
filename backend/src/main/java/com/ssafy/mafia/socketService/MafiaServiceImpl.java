package com.ssafy.mafia.socketService;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MafiaServiceImpl implements MafiaService{

	private final SimpMessageSendingOperations sendingOperations;
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}
