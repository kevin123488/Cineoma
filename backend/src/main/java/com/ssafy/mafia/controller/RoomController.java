package com.ssafy.mafia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.service.RoomService;

@RestController
//@RequestMapping("/room")
public class RoomController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private RoomService roomService;
	
	@GetMapping(value ="/room/{no}")
	public ResponseEntity<Room> roomInfo(@PathVariable("no") int no) throws Exception{
		Room room = roomService.roomInfo(no);
		System.out.println("123123123123");
		System.out.println(room.toString());
		if (room != null)
			return new ResponseEntity<Room>(room, HttpStatus.OK);
		else
			return new ResponseEntity<Room>(HttpStatus.NO_CONTENT);
	
		
	}
	
	@DeleteMapping(value = "/room/{no}")
	public ResponseEntity<String> deleteRoom(@PathVariable("no") int no) throws Exception{
		
		System.out.println("11111");
		roomService.deleteRoom(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
}
