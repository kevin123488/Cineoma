package com.ssafy.mafia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mafia.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
