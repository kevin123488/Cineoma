package com.ssafy.mafia.socketDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadyParamDto {
	
	//방 넘버
	private int roomNo;
	private String id;
	//모두가  ready면 true로 보내줌, 방장 외엔 의미없는 변수
	private boolean ifStart;
	//레디 상태를 표시함 true : ready 상태  f :  레디안함
	private boolean ifReady;
	//true 를 받으면 인게임으로 넘어가야함 , f : 유지
	private boolean StartGame;
}
