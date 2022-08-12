package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.User;

public interface UserService {
	public User login(User user) throws Exception;
	
	public User userInfo(String id) throws Exception;
	
	void registerUser(User user) throws Exception;
	void updateUser(User user) throws Exception;
	void deleteUser(String id) throws Exception;
	int idCheck(String checkId) throws Exception;
	List<User> getList(String id) throws Exception;
	// 검색어로 유저 검색
	List<User> SearchList(String word) throws Exception;
	// 방에 있는 유저 검색
	List<User> roomUser(int roomNo) throws Exception;
	//방에 참가한 상태인지
	public boolean checkUser(String id) throws Exception;
	
	void updateUserRoomNo(int roomNo, String id) throws Exception;
}
