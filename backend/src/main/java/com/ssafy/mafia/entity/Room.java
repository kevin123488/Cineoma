package com.ssafy.mafia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "room")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	@Id @GeneratedValue
	private int no;
	private int size;
	private String roomTitle;
//	private String hostId;
	
	@OneToOne
	@JoinColumn(name = "host_id")
	private User hostId;
	
	private String password;
	private boolean ifPassword;
	private int memberCnt;
}
