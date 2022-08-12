package com.ssafy.mafia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Transactional
	User findById(String id);
	
	@Transactional
	void deleteById(String id);
	
	// 닉네임으로 유저 검색
	@Query(value = "select * from user where nickname like %?%", nativeQuery =true)
	public List<User> findByWord(String word);
	
	@Transactional
	@Modifying
	@Query(value = "update user set room_no = ?1 where id = ?2",  nativeQuery =true)
	public int updateRoomNo(int roomNo, String id);
	
	// 해당 유저가 방에 들어가 있는 상태인지.
		@Query(value = "select count(*) from room_user where id =?",  nativeQuery =true)
	public int countById(String id);
		
	// 방번호로 방에 있는 유저 검색
	List<User> findAllByroomNo(int roomNo);
	
	//방에있는 유저 삭제/ 방 삭제
//	void deleteById(String id);
//	void deleteAllByRoomNo(int roomNo);
		
		
	
}
