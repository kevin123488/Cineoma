package com.ssafy.mafia.socketService;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.common.MafiaPlaingUser;
import com.ssafy.mafia.common.MafiaPlayStorage;
import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.socketDto.GameOutDto;
import com.ssafy.mafia.socketDto.RoomBreakDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameOutServiceImpl implements GameOutService{

	private final SimpMessageSendingOperations sendingOperations;

	@Override
	public void gameout(int roomNo, String id) throws Exception {
		GameOutDto result = new GameOutDto();
		
		MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(roomNo);
		
		for (MafiaPlaingUser it : mps.getPlaingUsers()) {
			if(id.equals(it.getId()))
			{
				it.setIfAlive(false);
				break;
			}
		}
		
		String winJob=mps.gameEndCheck();
		
		result.setId(id);
		result.setWinJob(winJob);
		
		sendingOperations.convertAndSend("/topic/sendMafia/"+roomNo+"/gameOut", result);
		
	}
	


}
