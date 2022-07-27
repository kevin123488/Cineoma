package com.ssafy.mafia.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
	@Id @GeneratedValue
	private int no;
	private String id;
	private String password;
	private String nickname;
	private String email;
	@Embedded
	private String image_path;
	private String user_name;
	private int my_point;
}
