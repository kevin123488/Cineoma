package com.ssafy.mafia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findById(String id);
	
	@Transactional
	void deleteById(String id);
	
	@Query(value = "select * from user where nickname like %?%", nativeQuery =true)
	public List<User> findByWord(String word);
	//List<User> findAllById(String id);
}
