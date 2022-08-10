package com.ssafy.mafia.socketController;
//package com.ssafy.mafia.socketController;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//import com.ssafy.mafia.dto.ReadyParamDto;
//import com.ssafy.mafia.dto.ReadyResultDto;
//import com.ssafy.mafia.entity.RoomUser;
//import com.ssafy.mafia.service.RoomService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
////레디 상태가 변화 할 때마다 방 전체에 알려주는 용도 및 게임 시작 사인을 보냄
//public class ReadyController {
//	 private final SimpMessageSendingOperations sendingOperations;
//	 @Autowired
//	 private RoomService roomService;
//	@MessageMapping("/receiveReady")
//    public void chat(ReadyParamDto paramDto) throws Exception {
//		ReadyResultDto result = new ReadyResultDto();
//		
//		//방장만 true로 보낼 수 있어야함, 나중에 방장인지 아닌지 체크 하는것도 만들까싶음
//		result.setStartGame(paramDto.isIfStart());
//		result.setId(paramDto.getId());
//		result.setIfReady(paramDto.isIfReady());
//		
//		int readyCount=0;
//		List<RoomUser> roomUserList = roomService.roomuserList(paramDto.getRoomNo());
//		for (RoomUser roomUser : roomUserList) {
//			if(!roomUser.isIfReady())
//				break;
//			readyCount++;
//		}
//		if(readyCount == roomUserList.size())
//			result.setIfStart(true);
//		else
//			result.setIfStart(false);
//		
//		//front에서 구독받을 uri로 보내줌
//		sendingOperations.convertAndSend("/topic/sendReady/"+paramDto.getRoomNo(), result);
//        return;
//    }
//}
