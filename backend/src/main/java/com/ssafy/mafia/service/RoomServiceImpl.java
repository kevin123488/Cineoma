package com.ssafy.mafia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room createRoom(Room room) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room roomInfo(int no) throws Exception {
		return roomRepository.findByNo(no);
	}

	@Override
	public void deleteRoom(int no) throws Exception {
		roomRepository.deleteByNo(no);
		
	}
	
	
	
}
