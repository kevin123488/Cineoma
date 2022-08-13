package com.ssafy.mafia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.dto.MemberResultDto;
import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.repository.RoomRepository;
import com.ssafy.mafia.repository.UserRepository;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
//	@Autowired
//	RoomUserRepository roomuserRepository;

	@Override
	public void createRoom(Room room) throws Exception {
		roomRepository.save(room);
//		User user = new RoomUser(room.getHostId(), room.getNo(), false);
//		roomuserRepository.save(roomUser);
		
	}

	@Override
	public Room roomInfo(int no) throws Exception {
		return roomRepository.findByNo(no);
	}

	@Override
	public void deleteRoom(int no) throws Exception {
		System.out.println("roomImpl no : " + no);
		roomRepository.deleteByNo(no);
		
	}

	@Override
	public List<Room> roomList() throws Exception {
		
		return roomRepository.findAll();
	}
	
	

//	//방 클릭했을때 보여주는 방의 유저들 유저 서비스에 있음 
//	@Override
//	public List<RoomUser> roomuserList(int roomNo) throws Exception {
//
//		return roomuserRepository.findAllByroomNo(roomNo);
//		
//	}
	
	@Override
	public int countUser(int no) throws Exception {
		
		return roomRepository.findByRoomNo(no);
	}

	@Override
	public List<MemberResultDto> roomuserList(int roomNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUser(String id) throws Exception {
		if(userRepository.findById(id).getRoomNo() == 1)
			return true;
		else 
		return false;
	}
	
	// 룸 이름으로 방 번호 찾기
	@Override
	public int findRoomNo(String roomTitle) throws Exception {
		roomRepository.findRoomNo(roomTitle);
		return 0;
	}

//	// 지금 참가한 방이 있는지
//	public boolean checkUser(String id) throws Exception{
//		
//		//속한 방이 있으면 false
//		if(roomuserRepository.countById(id) > 0) return false;
//		// 없으면 true
//		else return true;
//	}
	
	
}
