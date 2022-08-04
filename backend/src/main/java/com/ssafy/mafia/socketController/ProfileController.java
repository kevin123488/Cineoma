package com.ssafy.mafia.socketController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.dto.ProfileParamDto;
import com.ssafy.mafia.dto.ProfileResultDto;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {
	 private final SimpMessageSendingOperations sendingOperations;
	 
	 @Autowired
	 private UserService userService;
	 
	@MessageMapping("/receiveProfile")
    public void chat(ProfileParamDto paramDto) throws Exception {

		User user=userService.userInfo(paramDto.getId());
		ProfileResultDto result = new ProfileResultDto();
		//굳이 나눌 필요가 있을까?
		if(paramDto.getProgress().equals("out"))
		{
			result.setId(user.getId());
			System.out.println("out result : "+ result);
		}
		else if(paramDto.getProgress().equals("in"))
		{
			result.setId(user.getId());
			result.setImagePath(user.getImagePath());
			result.setIntro(user.getIntro());
			result.setNickName(user.getNickname());
			System.out.println("in result : "+ result);
			//front에서 구독받을 uri로 보내줌
		}
		else
		{
			System.out.println("ProfileController 에 파라미터 값 잘못들어옴");
		}
		
		sendingOperations.convertAndSend("/topic/sendProfile/"+paramDto.getRoomNo(), result);
        return;
    }
	

}
