package com.ssafy.mafia.socketDto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MafiaVoteFinishResultDto {
	
	private String progress;
	
	//뽑힌 사람의 아이디 닉네임
	//뽑힌사람이 없거나(넘어가기가 가장 많을때) 동률일때 -> "" 
    private String id;
    private String nickname;
    // 승리자 없을 때 -> "", 시민 승 -> citizen, 마피아 승 -> mafia, 미션자 승 -> mission
    private String winJob;
    
    
}
