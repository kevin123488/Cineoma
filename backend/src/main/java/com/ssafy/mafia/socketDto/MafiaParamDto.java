package com.ssafy.mafia.socketDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MafiaParamDto {
    
	//구분자
	private String progress;
	
	//방 번호 구분용
    private int roomNo;
	 // 유저의 닉네임을 저장하기 위한 변수
    private String nickName;
    private String id;
    private String job;
    //누구를 뽑았는지 id 값을 넣어줌
    private String vote;
    private String sessionId;
    
    
    //중립이 승리 했을 경우 알려줌
    private boolean ifWin;
    
    
  
}
