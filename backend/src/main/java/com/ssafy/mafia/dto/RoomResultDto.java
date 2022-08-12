package com.ssafy.mafia.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResultDto {
	
	List<MemberResultDto>  MemDto = new ArrayList<MemberResultDto>();
	
	private String roomTitle;
	
	private int roomNo;
	
	private int memberCnt;
	
	private boolean isPassword;
	
}
