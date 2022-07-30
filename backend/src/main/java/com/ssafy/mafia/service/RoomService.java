package com.ssafy.mafia.service;

import com.ssafy.mafia.entity.Room;

public interface RoomService {
	
	public Room createRoom(Room room) throws Exception;
	public Room roomInfo(int no) throws Exception;
	void deleteRoom(int no) throws Exception;
	
}
