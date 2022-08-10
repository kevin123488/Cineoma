package com.ssafy.mafia.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private boolean ifAlive=false;
}
