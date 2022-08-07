package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadyResultDto {
	
	private String id;
	
	//레디 상태를 표시함 true : ready 상태  f :  레디안함
	private boolean ifReady;
	//start가능한 상태인지 알려줌 , 방장에게만 의미있음
	private boolean ifStart;
	//방장만 true로 보낼 수 있어야함 true : 게임 시작 하겠다.  f :  대기 
	private boolean StartGame;
}
