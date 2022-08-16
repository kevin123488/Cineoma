package com.ssafy.mafia.socketController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.ssafy.mafia.common.MafiaPlaingUser;
import com.ssafy.mafia.common.MafiaPlayStorage;
import com.ssafy.mafia.common.MafiaStaticData;
import com.ssafy.mafia.socketDto.JoinUserDto;
import com.ssafy.mafia.socketDto.MafiaDayResultDto;
import com.ssafy.mafia.socketDto.MafiaParamDto;
import com.ssafy.mafia.socketDto.MafiaStartResultDto;
import com.ssafy.mafia.socketDto.MafiaVoteFinishResultDto;
import com.ssafy.mafia.socketDto.MafiaVoteResultDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//레디 상태가 변화 할 때마다 방 전체에 알려주는 용도 및 게임 시작 사인을 보냄
public class MafiaController {
	
	 private final SimpMessageSendingOperations sendingOperations;

	 
	 
	 @MessageMapping("/receiveMafia")
     public void mafia(MafiaParamDto paramDto) throws Exception {
		 
		 System.out.println("======================리시브 마피아=================================");
		 System.out.println(paramDto);
		 String progress = paramDto.getProgress();
		 
		 //static에 저장된 방정보
		 MafiaPlayStorage mps = MafiaStaticData.MafiaPlayStorageDtoMap.get(paramDto.getRoomNo());
		 
		 switch (progress) {
		 case "start":
		 {
			//세션 아이디랑 유저 아이디,접속한 방 번호로 접속중인 회원관리 
			MafiaStaticData.socketConnectedUserId.put(paramDto.getSessionId(), paramDto.getId());
			MafiaStaticData.socketConnectedUserRoomNo.put(paramDto.getSessionId(), paramDto.getRoomNo());
			
			
			
			//현재 시간 갱신
			if(mps.getTime().equals(""))
			{
				mps.setTime(getTime());
			}
			
			
			MafiaStartResultDto startResult = new MafiaStartResultDto();
			
			//result에 joinUsers값 만들어주는부분
			for (MafiaPlaingUser it : mps.getPlaingUsers()) {
				JoinUserDto jud = new JoinUserDto(it.getId(), it.getNickname(), it.getColor());
				startResult.getJoinUsers().add(jud);
			}
			//Progress, absoluteTime 공통부분이니 여기서 처리해줌
			startResult.setProgress(progress);
			
			startResult.setAbsoluteTime(mps.getTime());
			//result에 공통값 외에 바뀌는 부분을 넣어서 보내주는 부분
			System.out.println(mps.getPlaingUsers());
			for (MafiaPlaingUser it : mps.getPlaingUsers()) {
				if(paramDto.getId().equals(it.getId()))
				{
					it.setIfAlive(true);
					startResult.setHost(it.isIfHost());
					startResult.setJob(it.getJob());
					break;
				}
				
//				System.out.println("startResult");
			}
			System.out.println("각 개인에게 보내주는 메시지======================================");
			System.out.println("/topic/sendMafia/"+paramDto.getRoomNo()+"/"+paramDto.getId()+"\n"+startResult+"\n");
			
			sendingOperations.convertAndSend("/topic/sendMafia/"+paramDto.getRoomNo()+"/"+paramDto.getId(), startResult);
		 }
		break;

			
		case "day":
		{
			MafiaDayResultDto dayResult = new MafiaDayResultDto();
			
			
			
			MafiaPlaingUser mpu = mps.getMPU(paramDto.getId());
			
			dayResult.setColor(mpu.getColor());
			dayResult.setId(paramDto.getId());
			dayResult.setProgress(progress);
			
			mps.setVoteCount(mps.getVoteCount()+1);
			
			if(mps.getVoteCount()>mps.getAliveCount()/2)
			{
				mps.setVoteCount(0);
				dayResult.setAbsoluteTime(getTime());
				dayResult.setIfSkip(true);
			}
			else
				dayResult.setIfSkip(false);
			
			
			sendingOperations.convertAndSend("/topic/sendMafia/"+paramDto.getRoomNo(), dayResult);	
			
			
		}
		break;
		
		case "voteDay":
		{
			mps.setTime("");
			//미션자가 미션 완료한 경우
			if(paramDto.isIfWin())
			{
				mps.setMissionComplete(true);
			}
			
			MafiaVoteResultDto result = new MafiaVoteResultDto();
			
			result.setVotedId(paramDto.getVote());
			result.setId(paramDto.getId());
			result.setProgress(progress);
			
			//투표 기록
			if(mps.getVote().containsKey(paramDto.getVote()))
			{
				String votedId=paramDto.getVote();
				mps.getVote().put(votedId, mps.getVote().get(votedId)+1);
			}
			else
			{
				mps.getVote().put(paramDto.getVote(), 1);
			}

			System.out.println("===============뽑은 상태 표시===================");
			System.out.println(mps.getVote());
			
			
			sendingOperations.convertAndSend("/topic/sendMafia/"+paramDto.getRoomNo(), result);	
			System.out.println(mps.getVoteCount());
			
			mps.setVoteCount(mps.getVoteCount()+1);
			System.out.println(mps.getVoteCount());
			//모두 투표를 마친 경우
			System.out.println("살아있는 사람 수 : "+mps.getAliveCount());
			if(mps.getAliveCount()==mps.getVoteCount())
			{
				System.out.println("======================표 다 모였음============================");
				//투표 카운트 초기화
				mps.setVoteCount(0);
				
				MafiaVoteFinishResultDto result2 = new MafiaVoteFinishResultDto();
				int maxNum=0;
				String maxId="";
				for (Map.Entry<String, Integer> it : mps.getVote().entrySet()) {
					if(maxNum<it.getValue())
					{
						maxId=it.getKey();
						maxNum=it.getValue();
					}
					else if(maxNum==it.getValue())
					{
						maxId="";
					}
				}
				
				result2.setProgress("voteDayFinish");
				result2.setId(maxId);
				//초기값 "" 세팅 ""이 아니라면 새로 갱신됨
				result2.setNickname("");
				if(!maxId.equals(""))
				{
					result2.setNickname(mps.getMPU(maxId).getNickname());
					mps.kill(maxId);
					System.out.println("============죽은후 방 저장 상태 확인============================");
					System.out.println(mps);
					System.out.println("============죽은후 방 저장 상태 확인 끝============================");
				}
				
				String winJob=mps.gameEndCheck();
				result2.setWinJob(winJob);
				
				//투표 기록초기화
				mps.getVote().clear();
				System.out.println(result2);
				sendingOperations.convertAndSend("/topic/sendMafia/"+paramDto.getRoomNo(), result2);
			}
			
		}
		break;
		
		
		case "voteNight":
		{
			MafiaVoteFinishResultDto result = new MafiaVoteFinishResultDto();
			
			
			
			mps.setVoteCount(mps.getVoteCount()+1);
			String dieId="";
			if(paramDto.getJob().equals("doctor"))
			{
				//마피아가 아직 투표 안했음
				if(mps.getVoteCount()==1)
				{
					mps.setDoctorChosen(paramDto.getVote());
					break;
				}
			}
			else if(paramDto.getJob().equals("mafia"))
			{
				//의사가 아직 투표 안했음
				if(mps.isDoctorAlive() && mps.getVoteCount()==1)
				{
					mps.setMafiaChosen(paramDto.getVote());
					break;
				}
			}
			else
			{
				System.out.println("====================마피아 컨트롤러 : 밤 투표 직업이 닥터, 마피아가 아닌 이상한 값이 들어왔음==============");
				System.out.println("이상한 값 : " + paramDto.getJob());
			}
			
			//마피아와 닥터의 선택이 다른경우
			if(!paramDto.getVote().equals(mps.getMafiaChosen()))
			{
				dieId=mps.getMafiaChosen();
			}
				
			result.setProgress("voteNight");
			result.setId(dieId);
			result.setNickname("");
			if(!dieId.equals(""))
			{
				result.setNickname(mps.getMPU(dieId).getNickname());
				mps.kill(dieId);	
			}
			String winJob=mps.gameEndCheck();
			result.setWinJob(winJob);
				
			//투표 기록초기화
			mps.setVoteCount(0);
			mps.setDoctorChosen("");
			mps.setMafiaChosen("");
			sendingOperations.convertAndSend("/topic/sendMafia/"+paramDto.getRoomNo(), result);
			
			
		}
		break;
		
		default:
			break;
		}
		 
		 
		
        return;
    }
	 
	 
	 
	 public String getTime()
	 {
			LocalTime now = LocalTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
			String absoluteTime = now.format(formatter);
			
			return absoluteTime;
	 }
}
