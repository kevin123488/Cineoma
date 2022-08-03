package com.ssafy.mafia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Follow {
	@Id @GeneratedValue
	private int no;
	@Column(name = "my_id")
	private String myId;
	@Column(name = "follow_id")
	private String followId;
}

