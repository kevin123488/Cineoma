package com.ssafy.mafia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.common.MafiaPlaingUser;
import com.ssafy.mafia.common.MafiaPlayStorage;
import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.entity.Record;
import com.ssafy.mafia.entity.RoomUser;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.repository.RoomUserRepository;

@Service
public class RoomUserServiceImpl implements RoomUserService{
	
	@Autowired
	RoomUserRepository roomuserRepository;
	@Autowired
	UserService userService;
	@Autowired
	RecordService recordService;
	
	@Override
	public void createRoomUser(RoomUser roomUser) throws Exception {
		roomuserRepository.save(roomUser);
		User user = userService.userInfo(roomUser.getId());
		MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(roomUser.getRoomNo());
		ArrayList<Record> records=(ArrayList<Record>) recordService.get(roomUser.getId());
		int winRate;
		int denominator=0, numerator=0;
		for (Record record : records) {
			numerator+=record.getWinCount();
			denominator+=(record.getDrawCount()+record.getLoseCount()+record.getWinCount());
		}
		
		if(denominator==0)
		{
			winRate=0;
		}
		else
		{
			numerator*=100;
			winRate=numerator/denominator;
		}
		MafiaPlaingUser mpu = new MafiaPlaingUser();
		
		mpu.setId(user.getId());
		mpu.setImagePath(user.getImagePath());
		mpu.setIntro(user.getIntro());
		mpu.setNickname(user.getNickname());
		mpu.setWinRate(winRate);
		mpu.setIfReady(false);
		mps.getPlaingUsers().add(mpu);
		
	}



	@Override
	public void deleteRoomUser(String id) throws Exception {
		roomuserRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteRoomAllUser(int roomNo) throws Exception
	{
		roomuserRepository.deleteAllByRoomNo(roomNo);
	}


	
	
}
