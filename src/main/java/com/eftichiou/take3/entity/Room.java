package com.eftichiou.take3.entity;

import javax.persistence.*;
@Entity
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "password")
	private String password;

	@Transient
	private int adminId;

	@OneToOne(mappedBy = "room", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private User user;

	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room() {

	}

	public Room( String roomName, String password) {
		this.roomName = roomName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Room [id=" + id + ", roomName=" + roomName + ", password=" + password + "]";
	}
	
	
	
	
	
	

}
