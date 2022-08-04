package com.ssafy.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileResultDto {
	private String id;
	private String progress;
	private String nickName;
	private String imagePath;
	private String intro; 
//	private int roomNo;
	
	
}
