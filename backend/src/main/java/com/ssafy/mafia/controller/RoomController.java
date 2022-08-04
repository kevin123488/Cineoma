package com.ssafy.mafia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.dto.RoomResultDto;
import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.entity.RoomUser;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.RoomService;

@RestController
@RequestMapping(value = "/api")
public class RoomController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private RoomService roomService;
	RoomResultDto roomResultDto;
	
	//roomList - 참여인원(몇명인지), 방번호, 방이름 
	//로비 
	// 방 전체리스트, 친구목록, 방 참여 인원 수 
	//
	// 방 전체 리스트
	@GetMapping(value ="/room")
	public ResponseEntity<List<Room>> roomList() throws Exception{
		
//		List<Room> Test = roomService.roomList();
//		Test.get(0).
		return new ResponseEntity<List<Room>>(roomService.roomList(), HttpStatus.OK);
//				return null;
		
	}
	// 생성
	@PostMapping(value ="/room")
	public ResponseEntity<Room> createRoom(@RequestBody Room room) throws Exception{
		
		
		roomService.createRoom(room);
				return new ResponseEntity<Room>(room, HttpStatus.OK);
		
	}
	
	// 방을 클릭했을때 
	// 
	@GetMapping(value ="/room/{roomno}")
	public ResponseEntity<List<RoomUser>> roomInfo(@PathVariable("roomno") int roomno) throws Exception{
		System.out.println(roomno + "시발 왜 안되누 ;");
		List<RoomUser> ru = roomService.roomuserList(roomno);
		int test = ru.get(0).getRoomNo();
		System.out.println("test :" + test);
//		if (user != null)
//			return new ResponseEntity<User>(user, HttpStatus.OK);
//		else
//			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//		return new ResponseEntity<List<RoomUser>>(roomService.roomuserList(roomno), HttpStatus.OK);
		return null;
	
		
	}
	//방 입장
//	@PutMapping(value="/room/{no}")
//	public 
	
	//삭제
	@DeleteMapping(value ="/room/{no}") // db랑 이름 맞춰야함
	public ResponseEntity<String> removRoom(@PathVariable("no") int no) throws Exception{
		
		System.out.println("11111");
		roomService.deleteRoom(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
}
