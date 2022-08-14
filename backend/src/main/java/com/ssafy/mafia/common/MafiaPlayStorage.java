package com.ssafy.mafia.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ssafy.mafia.socketDto.ProfileUserDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//게임 진행 중 내용을 기록해둘 dto, map 에다가 방 번호로 index번호 넣고 관리할 계획
//dto라 하긴 힘들거 같지만 따로 패키지파고 만들기 애매해서 일단 dto에 생성해둠
@Data
@NoArgsConstructor
@ToString
public class MafiaPlayStorage {

	//t : 플레이 중 , f = 대기방 상태
	private boolean ifPlay=false;
	//대기방에서 게임 시작할때 유저 수만큼으로 바꿔주고 소켓 끊어지는 경우 1씩 줄여서 처리해줄거임 
	//게임방에서 대기방으로 오는경우 도 마찬가지?
	private int movingUserCount=0;
	//게임 시작 후 사용될 리스트
	private List<MafiaPlaingUser> plaingUsers= new ArrayList<>();
	//게임 시작 전 대기방에서 사용될 리스트
	private List<ProfileUserDto> profileUsers= new ArrayList<>();
	
	private Map<String, Integer> vote = new HashMap<String, Integer>();
	
	//투표완료한 숫자 기록 스킵 카운트도 이걸로 사용
	private int voteCount=0;
	
	//생존자 수
	private int aliveCount=0;
	
	
	//의사의 생존 여부
	private boolean doctorAlive=true;
	
	
	private String mafiaChosen="";
	private String doctorChosen="";
	
	public void gameEnd()
	{
		movingUserCount=plaingUsers.size();
		profileUsers.clear();
		ifPlay=false;
		//나머지는 방입장하면서 알아서 채워질거임
		
		
	}
	
	public void gameStart()
	{
		aliveCount=profileUsers.size();
		voteCount=0;
		profileUsers.clear();
		doctorAlive=true;
		mafiaChosen="";
		doctorChosen="";
		ifPlay=true;
		
		Random rand = new Random();
		ArrayList<String> colorList = new ArrayList<String>();
		ArrayList<String> jobList = new ArrayList<String>();
		jobList.add("mafia");
		jobList.add("police");
		jobList.add("doctor");
		jobList.add("citizen");
		jobList.add("citizen");
		colorList.add("red");
		colorList.add("blue");
		colorList.add("white");
		colorList.add("black");
		colorList.add("yellow");
		int maxIdx=5;
		int idx=0;
		for (ProfileUserDto pu : profileUsers) 
		{
			MafiaPlaingUser mpu = new MafiaPlaingUser();
			
			idx =rand.nextInt(maxIdx);
			mpu.setColor(colorList.get(idx));
			Collections.swap(colorList, idx, maxIdx);
			
			idx =rand.nextInt(maxIdx);
			mpu.setJob(jobList.get(idx));
			Collections.swap(jobList, idx, maxIdx);
			
			maxIdx--;
			
			mpu.setId(pu.getId());
			mpu.setImagePath(pu.getImagePath());
			mpu.setNickname(pu.getNickName());
			mpu.setWinRate(pu.getWinRate());
			mpu.setIfHost(pu.isIfCaptain());
			plaingUsers.add(mpu);
			
		}
	}
	
	public MafiaPlaingUser getMPU(String id)
	{
		for (MafiaPlaingUser mpu : plaingUsers) {
			if(id.equals(mpu.getId()))
			{
				return mpu;
			}
			
		}
		System.out.println("com.ssafy.mafia.common.MafiaPlayStorage.MafiaPlaingUser : 없는아이디 검색했음 널반환함");
		return null;
	}
	
	public void kill(String id)
	{
		for (MafiaPlaingUser mpu : plaingUsers) 
		{
			//살아 있는경우
			if(mpu.getId().equals(id))
			{
				mpu.setIfAlive(false);
				break;
			}
		}
	}
	
	public String gameEndCheck()
	{
		String result="";
		
		int aliveMafiacount=0;
		int aliveCitizencount=0;
		int aliveDoctorcount=0;
		int alivePolicecount=0;
		
		for (MafiaPlaingUser mpu : plaingUsers) 
		{
			//살아 있는경우
			if(mpu.isIfAlive())
			{
				switch (mpu.getJob()) {
				case "mafia":
					aliveMafiacount++;
					break;
				case "citizen":
					aliveCitizencount++;
					break;
				case "police":
					aliveCitizencount++;
					break;
				case "doctor":
					aliveCitizencount++;
					aliveDoctorcount++;
					break;
				default:
					break;
				}
			}
		}
		if(aliveDoctorcount==0)
		{
			doctorAlive=false;
		}
		if(aliveMafiacount==0)
		{
			result = "citizen";
			gameEnd();
		}
		if(aliveMafiacount>=aliveCitizencount)
		{
			result = "mafia";
			gameEnd();
		}
		
		return result;
	}
	
}


