package com.ssafy.mafia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mafia.entity.Record;
import com.ssafy.mafia.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepository recordRepository;
	@Override
	public List<Record> get(String id) throws Exception {
		return recordRepository.findAllById(id);
	}
	@Override
	public void registerUser(String id) throws Exception {
		Record record1 = new Record();
		record1.setId(id);
		record1.setType("mafia");
		recordRepository.save(record1);
		Record record2 = new Record();
		record2.setId(id);
		record2.setType("police");
		recordRepository.save(record2);
		Record record3 = new Record();
		record3.setId(id);
		record3.setType("doctor");
		recordRepository.save(record3);
		Record record4 = new Record();
		record4.setId(id);
		record4.setType("citizen");
		recordRepository.save(record4);
		
	}
	

}
