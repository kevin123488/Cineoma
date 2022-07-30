package com.ssafy.mafia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Record {
	@Id @GeneratedValue
	private int no;
	@OneToOne
	@JoinColumn(name = "id")
	private User user;
	@Column(name = "win_count")
	private int winCount;
	@Column(name = "lose_count")
	private int loseCount;
	@Column(name = "draw_count")
	private int drawCount;
}

