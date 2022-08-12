package com.ssafy.mafia.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

//게임 진행 중 내용을 기록해둘 dto, map 에다가 방 번호로 index번호 넣고 관리할 계획
//dto라 하긴 힘들거 같지만 따로 패키지파고 만들기 애매해서 일단 dto에 생성해둠
@Data
@NoArgsConstructor
public class MafiaPlayStorageDto {

	//t : 플레이 중 , f = 대기방 상태
	private boolean ifPlay=false;
	private List<PlaingUser> PlaingUsers= new ArrayList<>();
	private String progress;

	
	class PlaingUser{
		private String id;
		private String nickname;
		private String job;
		private String color;
		
		//t : 레디중 , f : 
		private boolean ifReady=false;
		private boolean ifAlive=false;
		
		
	}
	
}


