package com.ssafy.mafia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer>{
	void deleteById(String id);
	List<Record> findAllById(String id);
}
