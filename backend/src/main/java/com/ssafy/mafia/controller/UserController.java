package com.ssafy.mafia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.entity.Record;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.JwtServiceImpl;
import com.ssafy.mafia.service.RecordService;
import com.ssafy.mafia.service.RoomService;
import com.ssafy.mafia.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;
	@Autowired
	RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private RecordService recordService;
	//로그인
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			User loginUser = userService.login(user);
			List<Record> loginUserRecord = recordService.get(user.getId());
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("record", loginUserRecord);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = userService.userInfo(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping
	public ResponseEntity<String> signup(@RequestBody User user, HttpSession session, HttpServletResponse response)
			throws Exception {
		int found = userService.idCheck(user.getId());
		System.out.println(user.getId());
		if (found == 0) {
//			user.getRoom().setNo(1);
			userService.registerUser(user);
			userService.updateUserRoomNo(1, user.getId());
//			user.setRoom(roomService.roomInfo(1));
			recordService.registerUser(user.getId());
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "회원정보얻기", notes = "한명의 회원 정보를 얻는다. 성공시 해당 member객체 반환. 실패시  no_content 반환", response = User.class)
	@GetMapping(value = "/{userid}")
	public ResponseEntity<User> userInfo(@PathVariable("userid") String userid) throws Exception {
		User user = userService.userInfo(userid);
		System.out.println(user.toString());
		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원 수정", notes = "해당 회원의 정보를 얻는다. 성공시 member객체 반환", response = User.class)
	@PutMapping
	public ResponseEntity<User> modifyUser(@RequestBody User user) throws Exception {
		System.out.println(user.toString());
		
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 유저삭제
	@ApiOperation(value = "회원삭제", notes = "해당회원을 삭제시킨다.", response = String.class)
	@DeleteMapping(value = "/{userid}")
	public ResponseEntity<String> removeUser(@PathVariable("userid") String userid) throws Exception {
		System.out.println(userid);
		userService.deleteUser(userid);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
//	@ApiOperation(value = "회원 검색 목록", notes = "검색어에 맞는 유저목록을 보여준다.")
//	@GetMapping(value = "/list")
//	public ResponseEntity<List<User>> searchList(@RequestParam("id") String id) throws Exception{
//		return new ResponseEntity<List<User>>(userService.getList(id), HttpStatus.OK);
//	}
	
	@ApiOperation(value = "회원 검색 목록", notes = "검색어에 맞는 유저목록을 보여준다.")
	@GetMapping(value = "/list")
	public ResponseEntity<List<User>> searchList(@RequestParam("word") String word) throws Exception{
		System.out.println(word);
		//여기 고쳐야함 아이디 받아서 자기꺼 안나오게
		String id = "11";
		return new ResponseEntity<List<User>>(userService.SearchList(word,id), HttpStatus.OK);

				
	}	
	
}
