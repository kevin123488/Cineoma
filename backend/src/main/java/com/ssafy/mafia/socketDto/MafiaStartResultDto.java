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
public class MafiaStartResultDto {
	
private String progress;
	
    //시간값 hh:mm:ss, (24 기준)
    private String absoluteTime;
    //직업
    private String job;
    private boolean isHost;
    
    private List<JoinUserDto> joinUsers = new ArrayList<>();
    
//    public void addJoinUser(String id, String nickname, String color)
//    {
//    	joinUsers.add(new JoinUser(id,nickname,color));
//    }
//    
//    @AllArgsConstructor
//    class JoinUser{
//    	String id;
//    	String nickname;
//    	String color;
//    }
}
