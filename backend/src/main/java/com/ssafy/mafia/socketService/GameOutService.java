package com.ssafy.mafia.socketService;

import java.util.List;

import com.ssafy.mafia.entity.Room;


public interface GameOutService {
	
	public void gameout(int roomNo, String id) throws Exception;
	
}
