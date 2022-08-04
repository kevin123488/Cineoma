package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    //방 번호 구분용
    private int roomNo;
	 // 유저의 닉네임을 저장하기 위한 변수
    private String nickName;

    // 채팅내용을 저장하기 위한 변수
    private String content;

}
