package com.ssafy.mafia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Override
	public User login(User user) throws Exception {
		if (user.getId() == null || user.getPassword() == null)
			return null;
		return userRepository.checkLogin(user.getId(), user.getPassword());
	}

	@Override
	public User userInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(User user) throws Exception {
		
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
