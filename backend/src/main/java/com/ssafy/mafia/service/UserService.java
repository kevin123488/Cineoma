package com.ssafy.mafia.service;

import com.ssafy.mafia.entity.User;

public interface UserService {
	public User login(User user) throws Exception;
	public User userInfo(String id) throws Exception;
	void registerUser(User user) throws Exception;
	void updateUser(User user) throws Exception;
	void deleteUser(String id) throws Exception;
	int idCheck(String checkId) throws Exception;
}
