package com.ssafy.mafia.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "room")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
	@Id @GeneratedValue
	private int no;
	private int size;
	private String roomTitle;
	@Column(name = "host_id")
	private String hostId;
	
//	@OneToMany(mappedBy = "room")
//	private List<User> users = new ArrayList<User>();
//	
//	public void addUser(User user) {
//		this.users.add(user);
//		
//		if(user.getRoom() != this) {
//			user.setRoom(this);
//		}
//	}
	
	private String password;
	private boolean ifPassword;
	private int memberCnt;
}
