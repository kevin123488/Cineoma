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
	
	List<User> SearchList(String word) throws Exception;
	void updateUserRoomNo(int roomNo, String id) throws Exception;
}
