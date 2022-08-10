package com.ssafy.mafia.socketController;
//package com.ssafy.mafia.socketController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.mafia.common.MafiaStaticData;
//import com.ssafy.mafia.common.MafiaStaticData.IdAndRoomNo;
//import com.ssafy.mafia.dto.ProfileParamDto;
//import com.ssafy.mafia.dto.ProfileResultDto;
//import com.ssafy.mafia.dto.ProfileUserDto;
//import com.ssafy.mafia.entity.Record;
//import com.ssafy.mafia.entity.RoomUser;
//import com.ssafy.mafia.entity.User;
//import com.ssafy.mafia.service.ProfileService;
//import com.ssafy.mafia.service.RecordService;
//import com.ssafy.mafia.service.RoomService;
//import com.ssafy.mafia.service.RoomUserService;
//import com.ssafy.mafia.service.UserService;
//
//import lombok.RequiredArgsConstructor;
//
////사실상 입퇴실을 관리하는 컨트롤러
//@RestController
//@RequiredArgsConstructor
//public class ProfileController {
//	
//	 private final SimpMessageSendingOperations sendingOperations;
//	 
//	 @Autowired
//	 private UserService userService;
//	 @Autowired
//	 private RoomUserService roomUserService;
//	 @Autowired
//	 private RoomService roomService;
//	 @Autowired
//	 private RecordService recordService;
//	 @Autowired
//	 private ProfileService profileService;
//	@MessageMapping("/receiveProfile")
//    public void chat(ProfileParamDto paramDto) throws Exception {
//		
//		ProfileResultDto result = new ProfileResultDto();
//		
//		MafiaStaticData.socketConnectedUserId.put(paramDto.getSessionId(), paramDto.getId());
//		MafiaStaticData.socketConnectedUserRoomNo.put(paramDto.getSessionId(), paramDto.getRoomNo());
//		RoomUser roomUser = new RoomUser();
//		roomUser.setId(paramDto.getId());
//		roomUser.setRoomNo(paramDto.getRoomNo());
//		profileService.enterRoom(roomUser);
//		
//
//		
//		sendingOperations.convertAndSend("/topic/sendProfile/"+paramDto.getRoomNo()+"/", result);
//        return;
//    }
//	
//	//파라미터로 들어오는 User 정보 중 필요한 부분을 ProfileUserDto 에 복사함
//	private ProfileUserDto setProfileUserDto(User user)
//	{
//		ProfileUserDto dto = new ProfileUserDto();
//		
//		dto.setId(user.getId());
//		dto.setNickName(user.getNickname());
//		dto.setImagePath(user.getImagePath());
//		dto.setIntro(user.getIntro());
//		
//		
//		return dto;
//
//	}
//	
//
//}
