package com.ssafy.mafia.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileResultDto {
	//"in", "out" 으로 입 퇴실 구분함
	private String progress;
	//현재 룸에 있는 모든 유저(자신 포함)를 list에 담아서 보내줌
	List<ProfileUserDto> userList;
	
}
