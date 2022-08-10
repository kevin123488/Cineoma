package com.ssafy.mafia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Follow {
	@Id @GeneratedValue
	private int no;
	
	@ManyToOne
	@JoinColumn(name="myId")
	private User myId;
	
	@ManyToOne
	@JoinColumn(name = "followId")
	private User followId;
}

