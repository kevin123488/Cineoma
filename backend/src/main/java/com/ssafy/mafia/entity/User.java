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
public class User {
	@GeneratedValue
	private int no;
	@Column(nullable = false, unique = true)
	@Id
	private String id;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String email;
	@Column(name = "image_path")
	private String imagePath;
	@Column(nullable = false, name = "user_name")
	private String userName;
	@Column(name = "my_point")
	private int myPoint;
	private String intro;
	@Column(name = "room_no")
	private int roomNo;
//	@ManyToOne
//	@JoinColumn(name = "room_no")
//	private Room room;
//	
//	public void setRoom(Room room) {
//		if(this.room != null) {
//			this.room.getUsers().remove(this);
//		}
//		this.room = room;
//		if(!room.getUsers().contains(this)) {
//			room.addUser(this);
//		}
//	}




}
