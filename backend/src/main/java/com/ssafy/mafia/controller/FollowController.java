package com.ssafy.mafia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.entity.Follow;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	private FollowService followService;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "친구 목록 조회")
	@GetMapping(value = "{id}")
	public ResponseEntity<List<User>> followList(@PathVariable String id) throws Exception {
		return new ResponseEntity<List<User>>(followService.followList(id), HttpStatus.OK);
	}

	@ApiOperation(value = "친구 등록")
	@PostMapping
	public ResponseEntity<String> registerFollow(@RequestBody Follow follow) throws Exception {
		if(followService.registerFollow(follow)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "친구 삭제")
	@DeleteMapping
	public ResponseEntity<String> removeFollow(@RequestBody Follow follow) throws Exception{
		followService.deleteFollow(follow);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
