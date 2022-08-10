package com.ssafy.mafia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.RoomUser;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>{
	
	List<RoomUser> findAllByroomNo(int roomno);	
	
	@Query(value = "select count(*) from room_user where room_no =?",  nativeQuery =true)
	public int findByRoomNo(int no);
	
	@Query(value = "select count(*) from room_user where id =?",  nativeQuery =true)
	public int countById(String id);
	void deleteById(String id);
	void deleteAllByRoomNo(int roomNo);
	
}
