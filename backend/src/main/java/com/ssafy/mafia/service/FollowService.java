package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.Follow;

public interface FollowService {
	public List<Follow> followList(String id) throws Exception;
	public void registerFollow(String id) throws Exception;
	public void deleteFollow(String id) throws Exception;
}
