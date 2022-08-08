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
	
	@Autowired
	RoomUserRepository roomuserRepository;

	@Override
	public void createRoom(Room room) throws Exception {
		roomRepository.save(room);
		RoomUser roomUser = new RoomUser(room.getHostId(), room.getNo(), false);
		roomuserRepository.save(roomUser);
		
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
	
	@Override
	public int countUser(int no) throws Exception {
		
		return roomuserRepository.findByRoomNo(no);
	}

	
	public boolean checkUser(String id) throws Exception{
		
		//속한 방이 있으면 false
		if(roomuserRepository.countById(id) > 0) return false;
		// 없으면 true
		else return true;
	}
	
	
}
