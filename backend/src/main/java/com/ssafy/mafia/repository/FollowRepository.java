package com.ssafy.mafia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer>{
	List<Follow> FindAllByMyId(String id);
}
