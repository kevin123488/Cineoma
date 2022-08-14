package com.ssafy.mafia.controller;

import java.util.ArrayList;
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

import com.ssafy.mafia.dto.RoomEnterDto;
import com.ssafy.mafia.dto.RoomParamDto;
import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.RoomService;
import com.ssafy.mafia.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api")
public class RoomController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
//	private RoomParamDto roomDto;
//	RoomResultDto roomResultDto;
	
	//roomList - 참여인원(몇명인지), 방번호, 방이름 
	//로비 
	// 방 전체리스트, 친구목록, 방 참여 인원 수 
	//
	// 방 전체 리스트
	@GetMapping(value ="/room")
	@ApiOperation(value = "방 전체 리스트(로비화면)", notes ="전체 방 리스트를 조회한다.")
	public ResponseEntity<List<Room>> roomList() throws Exception{
//		List<RoomParamDto> RoomList = new ArrayList<RoomParamDto>();
//		List<Room> list = roomService.roomList();
//		System.out.println(list.size() + " 리 스 트 사 이 즈 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		return new ResponseEntity(roomService.roomList(), HttpStatus.OK);
//				return null;
		
	}
	// 생성
	@PostMapping(value ="/room")
	@ApiOperation(value = "방 생성", notes ="넘겨 받은 값으로 방을 생성한다.")
	@ApiImplicitParam(name = "room", value = "방 객체")
	public ResponseEntity<String> createRoom(@RequestBody Room room) throws Exception{
				
				// 생성한 방번호로 자신의 방번호를 업데이트
				System.out.println(room.getRoomTitle() + " 방만들기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ" );
				roomService.createRoom(room);
				System.out.println("방 만들어졌나 ?");
				System.out.println("룸 번호 확인 !!!!!! " + roomService.findRoomNo(room.getRoomTitle()) + "방장 아이디 확인 !!" + room.getHostId());
				
				System.out.println(roomService.findRoomNo(room.getHostId()));
				userService.updateUserRoomNo(roomService.findRoomNo(room.getHostId()), room.getHostId());
				
//				userService.updateRoomNo(user, roomdto.getNo());
				
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	
//	 방을 클릭했을때 
//	 방 제목, 닉네임 
	
	@GetMapping(value ="/room/{roomNo}")
	@ApiOperation(value = "방 정보", notes = "클릭한 방의 정보를 조회(참여유저 등)")
	@ApiImplicitParam(name = "roomNo", value ="클릭한 방의 번호")
	public ResponseEntity<List<RoomEnterDto>> roomInfo(@PathVariable("roomNo") int roomNo) throws Exception{
		System.out.println("asdasdasdasdasdasd");
		List<User> uslist = userService.roomUser(roomNo);
		System.out.println("asdasdasdasdasdasd");
		List<RoomEnterDto> enterlist = new ArrayList<RoomEnterDto>();
		for(User list : uslist) {
			RoomEnterDto mdto = new RoomEnterDto(list.getId(), list.getNickname(), "", roomNo);
			System.out.println(mdto.getId() + "방클릭했을때 아이디");
			enterlist.add(mdto);
		}
		
		if (enterlist != null)
			return new ResponseEntity<List<RoomEnterDto>>(enterlist, HttpStatus.OK);
		else
			return new ResponseEntity<List<RoomEnterDto>>(HttpStatus.NO_CONTENT);
	
		
	}
	//방 입장  // 방 번호 , 비밀번호, 유저 id 
	// 비밀번호 , 인원 수 , 방에 들어가있는지 체크,
	@PutMapping(value="/room/{roomNo}")
	@ApiOperation(value = "방 입장", notes = "방 입장을 위한 조건을 체크하여 결과를 리턴해줌")
	public ResponseEntity<String> enterRoom(@RequestBody RoomEnterDto dto) throws Exception{
		
		//방 번호
		int roomNo = dto.getNo();
		//유저 아이디
		String id = dto.getId();
		//유저가 입력한 방 비밀번호
		String userPw = dto.getPassword();
		//방의 비밀번호
		String checkPw = roomService.roomInfo(roomNo).getPassword();
		
		if(!checkPw.equals(userPw) || 5 <= roomService.countUser(roomNo) || !roomService.checkUser(id)) {
			System.out.println("못들어간다 으하하하");
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			System.out.println("들어가셈~~~~!!@#!@#!@#!@#!@#");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
	}
	
	//삭제
	@DeleteMapping(value ="/room/{roomNo}")
	@ApiOperation(value = "방 삭제", notes = "해당 방을 삭제")
	@ApiParam(name = "roomNo", value = "삭제하려는 방 번호")
	public ResponseEntity<String> removRoom(@PathVariable("roomNo") int no) throws Exception{

		roomService.deleteRoom(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
}
