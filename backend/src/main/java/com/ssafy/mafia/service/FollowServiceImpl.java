package com.ssafy.mafia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.Follow;
import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.repository.FollowRepository;
import com.ssafy.mafia.repository.UserRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowRepository followRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> followList(String id) throws Exception {
		List<Follow> fl = followRepository.FindAllByMyId(id);
		List<User> ul = new ArrayList<>();
		for (Follow f : fl) {
			ul.add(userRepository.findById(f.getFollowId()));
		}
		return ul;
	}

	@Override
	public boolean registerFollow(Follow follow) throws Exception {
		followRepository.save(follow);
		return true;

	}

	@Override
	public void deleteFollow(Follow follow) throws Exception {
		List<Follow> findFL = followRepository.FindAllByMyId(follow.getMyId());
		int fNo = 0; 
		for(Follow f : findFL) {
			if(f.getFollowId().equals(follow.getFollowId())) {
				fNo = f.getNo();
			}
		}
		followRepository.deleteById(fNo);
	}

}
