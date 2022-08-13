package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.dto.MemberResultDto;
import com.ssafy.mafia.entity.Room;

public interface RoomService {
	
	public List<Room> roomList() throws Exception;
	public void createRoom(Room room) throws Exception;
	public Room roomInfo(int no) throws Exception;
	void deleteRoom(int no) throws Exception;
	
	// 방에 있는 멤버 리스트
	public List<MemberResultDto> roomuserList(int roomNo) throws Exception;
	
	public int countUser(int no) throws Exception;
	
	public boolean checkUser(String id) throws Exception;
	
	// 방 번호 찾기
	public int findRoomNo(String roomTitle) throws Exception;
}
