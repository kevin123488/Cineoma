package com.ssafy.mafia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.Record;
import com.ssafy.mafia.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepository recordRepository;
	@Override
	public Record get(String id) throws Exception {
		return recordRepository.findById(id);
	}

}
