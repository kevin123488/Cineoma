package com.ssafy.mafia.service;

import java.util.List;

import com.ssafy.mafia.entity.Record;

public interface RecordService {
	public List<Record> get(String id) throws Exception; 
	void registerUser(String id) throws Exception;
}
