package com.ssafy.mafia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mafia.entity.RoomUser;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>{
	
	List<RoomUser> findAllByroomNo(int roomno);	
}
