package com.ssafy.mafia.service;

import java.util.List;

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
		User findUser = userRepository.findById(user.getId());
		if(user.getPassword().equals(findUser.getPassword())) return findUser;
		return null;
	}

	@Override
	public User userInfo(String id) throws Exception {
		return userRepository.findById(id);
	}

	@Override
	public void registerUser(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		User findUser = userRepository.findById(user.getId());
		findUser.setNickname(user.getNickname());
		findUser.setIntro(user.getIntro());
		findUser.setPassword(user.getPassword());
		userRepository.save(findUser);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userRepository.deleteById(id);
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		if(userRepository.findById(checkId) == null) return 0;
		else return 1;
	}

	@Override
	public List<User> getList(String id) throws Exception {
		//return userRepository.findAllById(id);
		return null;
	}
	
}
