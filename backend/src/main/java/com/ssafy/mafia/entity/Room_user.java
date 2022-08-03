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
public class Room_user {
	@Id @GeneratedValue
	@Column(name = "no")
	private int no;
	private int roomNo;
	String id;
}
