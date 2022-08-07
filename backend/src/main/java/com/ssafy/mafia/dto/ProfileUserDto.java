package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileUserDto {
	private String id;	
	private String nickName;
	private String imagePath;
	//자기소개
	private String intro;
	//해당 게임의 승률을 정수 형태로 보내준다
	private int winRate;
	//레디 상태를 표시함 true: ready완료
	private boolean ifReady;
}
