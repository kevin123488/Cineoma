package com.ssafy.mafia.common;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.mafia.socketDto.ProfileUserDto;

import lombok.Data;
import lombok.NoArgsConstructor;

//게임 진행 중 내용을 기록해둘 dto, map 에다가 방 번호로 index번호 넣고 관리할 계획
//dto라 하긴 힘들거 같지만 따로 패키지파고 만들기 애매해서 일단 dto에 생성해둠
@Data
@NoArgsConstructor
public class MafiaPlayStorage {

	//t : 플레이 중 , f = 대기방 상태
	private boolean ifPlay=false;
	//게임 시작 후 사용될 리스트
	private List<MafiaPlaingUser> plaingUsers= new ArrayList<>();
	//게임 시작 전 대기방에서 사용될 리스트
	private List<ProfileUserDto> profileUsers= new ArrayList<>();
	private String nowProgress="";

	
}


