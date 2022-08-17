package com.ssafy.mafia.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.mafia.service.RecordService;
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
	@Autowired
	RecordService recordService;
	
	
	
	
	//t : 플레이 중 , f = 대기방 상태
	private boolean ifPlay=false;
	//대기방에서 게임 시작할때 유저 수만큼으로 바꿔주고 소켓 끊어지는 경우 1씩 줄여서 처리해줄거임 
	//게임방에서 대기방으로 오는경우 도 마찬가지?
	private volatile int movingUserCount=0;
	//게임 시작 후 사용될 리스트
	private List<MafiaPlaingUser> plaingUsers= new ArrayList<>();
	//게임 시작 전 대기방에서 사용될 리스트
	private List<ProfileUserDto> profileUsers= new ArrayList<>();
	
	//누가 몇표 뽑혔는지 기록
	private Map<String, Integer> vote = new ConcurrentHashMap<String, Integer>();
	
	//낮투표 카운트할 변수
	private volatile int voteDayCount=0;
	//낮시간 스킵 투표 카운트 할 변수
	private volatile int voteSkipCount=0;
	//밤 투표 카운트 할 변수
	private volatile int voteNightCount=0;
	
	//생존자 수
	private volatile int aliveCount=0;
	
	
	//의사의 생존 여부
	private volatile boolean doctorAlive=true;
	
	//미션자가 미션 완료한 경우
	private volatile boolean missionComplete=false;
	
	//방 전체 시간 확인용
	private volatile String time="";
	
	private String policeId="";
	
	private String mafiaChosen="";
	private String doctorChosen="";
	
	public void gameEnd(String winJob) throws Exception
	{
		movingUserCount=plaingUsers.size();
		
	
		for (MafiaPlaingUser mpu : plaingUsers) {
			
			if(mpu.getId().equals("doctor") && winJob.equals("citizen"))
			{
				recordService.recordUpdate(mpu.getId(), mpu.getJob(), "ture");
			}
			else if(mpu.getId().equals("citizen") && winJob.equals("doctor"))
			{
				recordService.recordUpdate(mpu.getId(), mpu.getJob(), "ture");
			}
			else if(mpu.getJob().equals(winJob))
			{
				recordService.recordUpdate(mpu.getId(), mpu.getJob(), "ture");
			}
			else
			{
				recordService.recordUpdate(mpu.getId(), mpu.getJob(), "lose");
			}
			
		}
		
		
		
		
		profileUsers.clear();
		ifPlay=false;
		//나머지는 방입장하면서 알아서 채워질거임
		
		
	}
	

	public void gameStart()
	{
		aliveCount=profileUsers.size();
		movingUserCount=profileUsers.size();
//		profileUsers.clear();
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
			System.out.println("===============================================================");
			System.out.println("colorList : "+idx);
			mpu.setColor(colorList.get(idx));
			Collections.swap(colorList, idx, maxIdx-1);
			
			idx =rand.nextInt(maxIdx);
			System.out.println("jobList : "+idx);
			mpu.setJob(jobList.get(idx));
			if(mpu.getJob().equals("police"))
			{
				policeId=pu.getId();
			}
			Collections.swap(jobList, idx, maxIdx-1);
			
			maxIdx--;
			
			mpu.setId(pu.getId());
			mpu.setImagePath(pu.getImagePath());
			mpu.setNickname(pu.getNickName());
			mpu.setWinRate(pu.getWinRate());
			mpu.setIfHost(pu.isIfCaptain());
			plaingUsers.add(mpu);
			System.out.println(mpu);
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
				aliveCount--;
				mpu.setIfAlive(false);
				break;
			}
		}
	}
	
	public String gameEndCheck() throws Exception
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
					alivePolicecount++;
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
		if(missionComplete && alivePolicecount>0)
		{
			result = "police";
			gameEnd(result);
		}
		else if(aliveDoctorcount==0)
		{
			doctorAlive=false;
		}
		else if(aliveMafiacount==0)
		{
			result = "citizen";
			gameEnd(result);
		}
		else if(aliveMafiacount>=aliveCitizencount)
		{
			result = "mafia";
			gameEnd(result);
		}
		
		return result;
	}
	
	void recordUpdate(String id, String type, String winLose) throws Exception
	{
		for (MafiaPlaingUser mpu : plaingUsers) {
			if(mpu.getJob().equals(type))
			{
				recordService.recordUpdate(id, type, "win");
			}
			else
			{
				
			}
		}
		
		
	}
	
}


