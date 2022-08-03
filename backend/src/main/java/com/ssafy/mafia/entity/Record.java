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
@Getter
@Setter
public class Record {
	@Id
	@GeneratedValue
	private int no;
	private String id;
	@Column(name = "win_count")
	private int winCount = 0;
	@Column(name = "lose_count")
	private int loseCount = 0;
	@Column(name = "draw_count")
	private int drawCount = 0;
	private String type; // 직업을 나타냄.
}
