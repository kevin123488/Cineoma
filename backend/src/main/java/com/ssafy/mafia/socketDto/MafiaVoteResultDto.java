package com.ssafy.mafia.socketDto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MafiaVoteResultDto {
	
	private String progress;
	
	//뽑은사람
    private String id;
    //뽑힌사람
    private String votedId;
    
    
}
