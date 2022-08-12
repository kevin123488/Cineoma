package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEnterDto {
	
	String id;
	String nickname;
	String password;
	int no;

}
