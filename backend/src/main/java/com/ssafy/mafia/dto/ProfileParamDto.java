package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileParamDto {
	
	//세션 아이디
	private String sessionId;
	//입퇴실하는 유저의 id
	private String id;
	//입퇴실 할 방 넘버
	private int roomNo;
	
	
}
