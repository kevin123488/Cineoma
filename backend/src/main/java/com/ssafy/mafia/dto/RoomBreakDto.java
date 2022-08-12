package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomBreakDto {
	
	//폭파될 방 번호, 방장만 보낼수 잇어야함
	private int roomNo;
}
