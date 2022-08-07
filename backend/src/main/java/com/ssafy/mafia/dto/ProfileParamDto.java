package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileParamDto {
	//"in", "out" 으로 입 퇴실 구분함
	private String progress;
	//입퇴실하는 유저의 id
	private String id;
	//입퇴실 할 방 넘버
	private int roomNo;
	
	
}
