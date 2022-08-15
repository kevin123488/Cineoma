package com.ssafy.mafia.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.service.RecordService;
import com.ssafy.mafia.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/record")
public class RecordController {
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private RecordService recordService;
//
//	@ApiOperation(value = "전적 갱신", notes = "파라미터로 아이디와 승패, 직업을 받아 전적을 갱신한다.", response = Map.class)
//	@GetMapping()
//	public ResponseEntity<String> updateRecord(@RequestParam Map<String, String> map) throws Exception {
//		if(recordService.recordUpdate(map))	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		else return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
//	}
}
