package com.ssafy.mafia.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.ssafy.mafia.common.MafiaPlayStorage;
import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.repository.RoomRepository;
import com.ssafy.mafia.socketService.GameOutService;
import com.ssafy.mafia.socketService.ProfileService;
import com.ssafy.mafia.socketService.RoomBreakService;

@Configuration
public class StompInterceptor {

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ProfileService profileService;
	@Autowired
	RoomBreakService roomBreakService;
	@Autowired
	GameOutService gameOutService;
	
	
	//연결끊김 감지용
	 @EventListener
	    public void onDisconnectEvent(SessionDisconnectEvent event) throws Exception
	 {
		 //세션 아이디 받기
		 String sessionId=event.getSessionId();
		 System.out.println(sessionId);
		 
		 //세션 아이디를 바탕으로 룸넘버, 아이디 받기
		 String id = MafiaStaticData.socketConnectedUserId.get(sessionId);
		 int roomNo = MafiaStaticData.socketConnectedUserRoomNo.get(sessionId);
		 //끊김 감지기 때문에 저장된 세션 아이디 기반 내용들 삭제
		 MafiaStaticData.socketConnectedUserId.remove(sessionId);		 
		 MafiaStaticData.socketConnectedUserRoomNo.remove(sessionId);
		 
		 //저장된 방 정보
		 MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(roomNo);
		 
		 //대기방->게임, 게임->대기방 이동중인 유저가 있다면 그 수만큼 나갈 때 처리안해줌
		 if(mps.getMovingUserCount()>0)
		 {
			 mps.setMovingUserCount(mps.getMovingUserCount()-1);
		 }
		 else
		 {
			 //db에서 룸 정보 가져오기
			 Room room= roomRepository.findByNo(roomNo);
			 
			 //방장이 나간경우
			 if(room.getHostId().equals(id))
			 {
				 //방에 방 폭파 메시지 전송
				 roomBreakService.sendBreakMsg(roomNo);
				 
				 //방 전체 인원들 나가기 처리
				 for (; 0 < mps.getPlaingUsers().size();) {
					 profileService.exitRoom(mps.getPlaingUsers().get(0).getId()
							 , roomNo);
				}
				//db에서 방 정보 삭제
				roomRepository.deleteByNo(roomNo); 
				//서버 static 에서 방 정보 삭제
				MafiaStaticData.MafiaPlayStorageDtoMap.remove(roomNo);
			 }
			 //일반 유저가 나간경우
			 else
			 {
				 //게임 중인 경우
				 if(mps.isIfPlay())
				 {
					 gameOutService.gameout(roomNo, id);
					 profileService.exitRoom(id, roomNo);
				 }
				 //대기방인 경우
				 else
				 {
					 profileService.exitRoom(id, roomNo);
				 }
			 }
		 }
		
		 
		 
	 
	 }
}
