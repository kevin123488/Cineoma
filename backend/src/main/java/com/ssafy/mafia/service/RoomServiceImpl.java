package com.ssafy.mafia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.entity.RoomUser;
import com.ssafy.mafia.repository.RoomRepository;
import com.ssafy.mafia.repository.RoomUserRepository;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;
	RoomUserRepository roomuserRepository;

	@Override
	public void createRoom(Room room) throws Exception {
		roomRepository.save(room);
		
	}

	@Override
	public Room roomInfo(int no) throws Exception {
		return roomRepository.findByNo(no);
	}

	@Override
	public void deleteRoom(int no) throws Exception {
		roomRepository.deleteByNo(no);
		
	}

	@Override
	public List<Room> roomList() throws Exception {
		
		return roomRepository.findAll();
	}

	@Override
	public List<RoomUser> roomuserList(int roomNo) throws Exception {

		return roomuserRepository.findAllByroomNo(roomNo);
	}
	
	
	
}
