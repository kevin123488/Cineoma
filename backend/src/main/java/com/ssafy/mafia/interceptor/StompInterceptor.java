package com.ssafy.mafia.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
public class StompInterceptor {

	//연결끊김 감지용
	 @EventListener
	    public void onDisconnectEvent(SessionDisconnectEvent event)
	 {
		 event.getSessionId();
	    }
}
