package com.ssafy.mafia.socketService;

import java.util.List;

import com.ssafy.mafia.entity.Room;


public interface ProfileService {
	
	public void enterRoom(String id, int roomNo) throws Exception;
	public void exitRoom(String id, int roomNo) throws Exception;
	
}
