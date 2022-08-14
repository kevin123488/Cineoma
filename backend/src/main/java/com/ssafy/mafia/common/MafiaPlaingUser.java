package com.ssafy.mafia.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MafiaPlaingUser {
	private String id="";
	private String nickname="";
	private String job="";
	private String color="";
	private String intro="";
	private String imagePath="";
	
	private int winRate=0;
	//t : 레디중 , f : 
	private boolean ifReady=false;
	//게임으로 들어 올때 true로 바꿔줄 계획
	private boolean ifAlive=false;
	private boolean ifHost=false;
}
