package com.ssafy.mafia.common;

import java.util.HashMap;
import java.util.Map;

public class MafiaStaticData {
	//K = roomNo, V= 게임 진행 상황 저장용 dto
	public static Map<Integer, MafiaPlayStorage> MafiaPlayStorageDtoMap = new HashMap<>();
	//K = Sessionid, V= id
	public static Map<String, String> socketConnectedUserId = new HashMap<>();
	//K = Sessionid, V= roomNo
	public static Map<String, Integer> socketConnectedUserRoomNo = new HashMap<>();
	
	
	
	
}
