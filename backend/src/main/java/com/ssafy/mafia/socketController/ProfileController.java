package com.ssafy.mafia.socketController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mafia.dto.ProfileParamDto;
import com.ssafy.mafia.dto.ProfileResultDto;
import com.ssafy.mafia.dto.ProfileUserDto;
import com.ssafy.mafia.entity.Record;
import com.ssafy.mafia.entity.RoomUser;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.service.RecordService;
import com.ssafy.mafia.service.RoomService;
import com.ssafy.mafia.service.RoomUserService;
import com.ssafy.mafia.service.UserService;

import lombok.RequiredArgsConstructor;

//사실상 입퇴실을 관리하는 컨트롤러
@RestController
@RequiredArgsConstructor
public class ProfileController {
	
	 private final SimpMessageSendingOperations sendingOperations;
	 
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private RoomUserService roomUserService;
	 @Autowired
	 private RoomService roomService;
	 @Autowired
	 private RecordService recordService;
	 
	@MessageMapping("/receiveProfile")
    public void chat(ProfileParamDto paramDto) throws Exception {
		
		ProfileResultDto result = new ProfileResultDto();
		
		//Progress와 userList에 나가는 유저 하나만 담아서 보냄
		if(paramDto.getProgress().equals("out"))
		{
			ProfileUserDto pfuDto = new ProfileUserDto();
			pfuDto.setId(paramDto.getId());
			List<ProfileUserDto> list = new ArrayList<ProfileUserDto>();
			list.add(pfuDto);
			result.setUserList(list);
			result.setProgress("out");
//			System.out.println("out result : "+ result);
			roomUserService.deleteRoomUser(paramDto.getId());
		}
		else if(paramDto.getProgress().equals("in"))
		{
			List<ProfileUserDto> list = new ArrayList<ProfileUserDto>();
			List<RoomUser> roomUserList = roomService.roomuserList(paramDto.getRoomNo());
			for (int i = 0; i < roomUserList.size(); i++) {
				
				RoomUser roomUser=roomUserList.get(i);
				List<Record> recordList = recordService.get(paramDto.getId());//해당 아이디 승률정보 가져오기
				User user = userService.userInfo(roomUser.getId());//해당 아이디 user 정보 가져오기
				ProfileUserDto pud=setProfileUserDto(user);//redeay 승률 제외한 모든것 세팅
				pud.setIfReady(roomUser.isIfReady());//ready 세팅
				for (Record record : recordList) {
					//마피아 구분할것 나중에 바뀔 수 있음
					if(record.getType().equals("mafia"))
					{
						int tmpWin=record.getWinCount();
						int tmpSum =tmpWin+record.getLoseCount();
						if(tmpSum == 0)
							pud.setWinRate(0);
						else
							pud.setWinRate((tmpWin*100)/tmpSum);
						
						break;
					}
				}
				
				list.add(pud);
			}
			result.setUserList(list);
			result.setProgress("in");
		}
		else
		{
			System.out.println("ProfileController 에 파라미터 값 잘못들어옴");
		}
		
		sendingOperations.convertAndSend("/topic/sendProfile/"+paramDto.getRoomNo(), result);
        return;
    }
	
	//파라미터로 들어오는 User 정보 중 필요한 부분을 ProfileUserDto 에 복사함
	private ProfileUserDto setProfileUserDto(User user)
	{
		ProfileUserDto dto = new ProfileUserDto();
		
		dto.setId(user.getId());
		dto.setNickName(user.getNickname());
		dto.setImagePath(user.getImagePath());
		dto.setIntro(user.getIntro());
		
		
		return dto;

	}
	

}
