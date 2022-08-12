package com.ssafy.mafia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Room findByNo(int no);
	@Query(value = "select * from room where no != 1", nativeQuery = true)
	List<Room> findAll();
	@Transactional
	void deleteByNo(int no);
	
	
	// 방에 몇명인지 검색
	@Query(value = "select count(*) from user where room_no =?",  nativeQuery =true)
	public int findByRoomNo(int no);
	
	@Transactional
	@Modifying
	@Query(value = "update room set member_cnt = member_cnt+1 where no = ?1",  nativeQuery =true)
	public int memberCntPlus(int roomNo);
	
	@Transactional
	@Modifying
	@Query(value = "update room set member_cnt = member_cnt-1 where no = ?1",  nativeQuery =true)
	public int memberCntminus(int roomNo);

	
	
//	@Query(value ="delete from room where no = ?", nativeQuery = true)
//	void deleteByNo(int no);
//	List<MemberResultDto> findAllById(String id);
	
}
