package com.ssafy.mafia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.RoomUser;
import com.ssafy.mafia.repository.RoomUserRepository;

@Service
public class RoomUserServiceImpl implements RoomUserService{
	
	@Autowired
	RoomUserRepository roomuserRepository;

	@Override
	public void createRoomUser(RoomUser roomUser) throws Exception {
		roomuserRepository.save(roomUser);
		
	}



	@Override
	public void deleteRoomUser(String id) throws Exception {
		roomuserRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteRoomAllUser(int roomNo) throws Exception
	{
		roomuserRepository.deleteAllByRoomNo(roomNo);
	}


	
	
}
