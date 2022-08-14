package com.ssafy.mafia.socketController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.RecordService;
import com.ssafy.mafia.service.RoomService;
import com.ssafy.mafia.service.UserService;
import com.ssafy.mafia.socketDto.ProfileParamDto;
import com.ssafy.mafia.socketDto.ProfileResultDto;
import com.ssafy.mafia.socketDto.ProfileUserDto;
import com.ssafy.mafia.socketService.ProfileService;

import lombok.RequiredArgsConstructor;

//사실상 입퇴실을 관리하는 컨트롤러
@RestController
@RequiredArgsConstructor
public class ProfileController {
	
	 private final SimpMessageSendingOperations sendingOperations;
	 
	
	 @Autowired
	 private ProfileService profileService;
	
	 
	 @MessageMapping("/receiveProfile")
     public void enterRoom(ProfileParamDto paramDto) throws Exception {
		
		ProfileResultDto result = new ProfileResultDto();
		
	
		
		//세션 아이디랑 유저 아이디,접속한 방 번호로 접속중인 회원관리 
		MafiaStaticData.socketConnectedUserId.put(paramDto.getSessionId(), paramDto.getId());
		MafiaStaticData.socketConnectedUserRoomNo.put(paramDto.getSessionId(), paramDto.getRoomNo());
		System.out.println(MafiaStaticData.socketConnectedUserId);
		System.out.println(MafiaStaticData.socketConnectedUserRoomNo);
		
		//static 저장 공간에 result에 필요할 값들을 넣어줌
		profileService.enterRoom(paramDto.getId(), paramDto.getRoomNo());
		

		result.setProgress("in");
		result.setUserList(MafiaStaticData.MafiaPlayStorageDtoMap.get(paramDto.getRoomNo()).getProfileUsers());
		sendingOperations.convertAndSend("/topic/sendProfile/"+paramDto.getRoomNo()+"/", result);
        return;
    }
	

}
