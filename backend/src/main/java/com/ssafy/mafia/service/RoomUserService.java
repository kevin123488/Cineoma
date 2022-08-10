package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.entity.RoomUser;

public interface RoomUserService {
	
	public void createRoomUser(RoomUser roomUser) throws Exception;
	public void deleteRoomUser(String id) throws Exception;
	public void deleteRoomAllUser(int roomNo) throws Exception;
}
