package com.ssafy.mafia.socketDto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MafiaDayResultDto {
	
	private String progress;
	
    private String id;
    //시간값 hh:mm:ss, (24 기준)
    private String absoluteTime;
    private String color;
    //과반수 이상 나올 경우 true 
    private boolean ifSkip;
    
    
}
