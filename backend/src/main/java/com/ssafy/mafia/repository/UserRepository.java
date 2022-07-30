package com.ssafy.mafia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findById(String id);
	void deleteById(String id);
	//List<User> findAllById(String id);
}
