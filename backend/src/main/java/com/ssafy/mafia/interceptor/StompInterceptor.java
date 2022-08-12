package com.ssafy.mafia.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.ssafy.mafia.common.MafiaPlayStorage;
import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.entity.Room;
import com.ssafy.mafia.repository.RoomRepository;
import com.ssafy.mafia.socketService.ProfileService;

@Configuration
public class StompInterceptor {

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ProfileService profileService;
	//연결끊김 감지용
	 @EventListener
	    public void onDisconnectEvent(SessionDisconnectEvent event) throws Exception
	 {
		 String sessionId=event.getSessionId();
		 String id = MafiaStaticData.socketConnectedUserId.get(sessionId);
		 int roomNo = MafiaStaticData.socketConnectedUserRoomNo.get(sessionId);
		 
		 MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(roomNo);
		 
		 Room room= roomRepository.findByNo(roomNo);
		 
		 //방장이 나간경우
		 if(room.getHostId().getId().equals(id))
		 {
			 
		 }
		 else
		 {
			 //게임 중인 경우
			 if(mps.isIfPlay())
			 {
				 
			 }
			 //대기방인 경우
			 else
			 {
				 profileService.exitRoom(id, roomNo);
			 }
		 }
		 
		 
	 
	 }
}
