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
public class RoomUser {
	@Id @GeneratedValue
	@Column(name = "no")
	private int no;
	@Column(nullable = false, unique = true)//한명이 여러방에 못들어가도록 만들어야함
	private String id;
	private int roomNo;
	private boolean ifReady;
}
