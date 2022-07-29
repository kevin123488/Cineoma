package com.ssafy.mafia.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
	@Id @GeneratedValue
	private int no;
	@Column(nullable = false, unique = true)
	private String id;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String email;
	private String image_path;
	@Column(nullable = false)
	private String user_name;
	private int my_point;
	private String intro;
}
