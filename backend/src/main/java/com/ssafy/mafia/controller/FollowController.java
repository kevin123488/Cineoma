package com.ssafy.mafia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/follow")
public class FollowController {
	
//	@Autowired
//	private FollowService followService;
//	
//	/*@ApiOperation(value = "친구 목록", notes = "친구 목록을 보여준다.")
//	@GetMapping(value = "/list")
//	public ResponseEntity<List<String>> searchList(@RequestParam("id") String id) throws Exception{
//		return new ResponseEntity<List<String>>(followService.followList(id), HttpStatus.OK);
//	}*/
}
