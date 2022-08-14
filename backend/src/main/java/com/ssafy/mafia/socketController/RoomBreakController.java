//package com.ssafy.mafia.socketController;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//import com.ssafy.mafia.common.MafiaPlayStorage;
//import com.ssafy.mafia.common.MafiaStaticData;
//import com.ssafy.mafia.service.RoomService;
//import com.ssafy.mafia.socketDto.ProfileUserDto;
//import com.ssafy.mafia.socketDto.ReadyParamDto;
//import com.ssafy.mafia.socketDto.ReadyResultDto;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
////레디 상태가 변화 할 때마다 방 전체에 알려주는 용도 및 게임 시작 사인을 보냄
//public class RoomBreakController {
//	 private final SimpMessageSendingOperations sendingOperations;
//
//	 
//	 
//	 @MessageMapping("/receiveReady")
//     public void ready(ReadyParamDto paramDto) throws Exception {
//
//		MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(paramDto.getRoomNo());
//		
//		ReadyResultDto result = new ReadyResultDto();
//		
//		//방장만 true로 보낼 수 있어야함, 나중에 방장인지 아닌지 체크 하는것도 만들까싶음
//		result.setStartGame(paramDto.isIfStart());
//		
//		//게임 시작 하는 경우
//		if(paramDto.isIfStart())
//		{
//			//대기방 유저 정보를 이용하여 게임 시작 유저 정보 반영
//			mps.gameStart();
//			mps.setMovingUserCount(mps.getProfileUsers().size());
//			result.setId(paramDto.getId());
//			result.setIfReady(true);
//			result.setStartGame(true);
//			result.setStartGame(true);
//			
//		}
//		//대기방 상태
//		else
//		{
//			result.setId(paramDto.getId());
//			result.setIfReady(paramDto.isIfReady());
//			
//			int readyCount=0;
//			ArrayList<ProfileUserDto> pfuList = (ArrayList<ProfileUserDto>) mps.getProfileUsers();
//			//ready 상태변경 겸 레디 유저수 카운트
//			for (ProfileUserDto profileUserDto : pfuList) {
//				if(profileUserDto.getId().equals(paramDto.getId()))
//				{
//					profileUserDto.setIfReady(paramDto.isIfReady());
//					result.setIfReady(paramDto.isIfReady());
//				}
//				
//				if(profileUserDto.isIfReady())
//					readyCount++;
//			}
//			
//			
//			//start 가능 상태 보낼 부분
//			if(readyCount == pfuList.size() && pfuList.size()==5)
//				result.setIfStart(true);
//			else
//				result.setIfStart(false);
//			
//			//front에서 구독받을 uri로 보내줌
//		}
//		
//		
//		
//		sendingOperations.convertAndSend("/topic/sendReady/"+paramDto.getRoomNo(), result);
//        return;
//    }
//}
