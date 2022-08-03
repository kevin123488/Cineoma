package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.Follow;
import com.ssafy.mafia.entity.User;

public interface FollowService {
	public List<User> followList(String id) throws Exception;
	public boolean registerFollow(Follow follow) throws Exception;
	public void deleteFollow(Follow follow) throws Exception;
}
