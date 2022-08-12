package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomParamDto {
	private int no;
	private int size;
	private String roomTitle;
	private String hostId;
	private String password;
	private boolean ifPassword;
	private int memberCnt;
	
}
