package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.entity.RoomUser;

public interface RoomService {
	
	public List<Room> roomList() throws Exception;
	public void createRoom(Room room) throws Exception;
	public Room roomInfo(int no) throws Exception;
	void deleteRoom(int no) throws Exception;
	public List<RoomUser> roomuserList(int roomNo) throws Exception;
	
}
