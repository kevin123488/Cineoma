package com.ssafy.mafia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Room findByNo(int no);
	List<Room> findAll();
	@Transactional
	void deleteByNo(int no);
	
//	@Query(value ="delete from room where no = ?", nativeQuery = true)
//	void deleteByNo(int no);
//	List<MemberResultDto> findAllById(String id);
	
}
