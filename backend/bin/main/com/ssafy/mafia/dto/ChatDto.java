package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatDto {
	 // 유저의 닉네임을 저장하기 위한 변수
    private String nickName;

    // 채팅내용을 저장하기 위한 변수
    private String content;

}
