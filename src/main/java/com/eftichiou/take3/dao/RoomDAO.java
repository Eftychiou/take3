package com.eftichiou.take3.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.security.auth.login.Configuration;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.eftichiou.take3.entity.Room;
import com.eftichiou.take3.entity.User;
import com.eftichiou.take3.exceptions.CustomException;
import com.eftichiou.take3.rest.Controllers;

import tools.Tools;

@Repository
public class RoomDAO implements IRoomDAO {
	
	private EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(Controllers.class);
	
	@Autowired
	public RoomDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}



	@Override
	public Boolean addRoom(Room room) {
		
		try {
			Session session = entityManager.unwrap(Session.class);
			User user = session.get(User.class, room.getAdminId());
			session.beginTransaction();
			user.setRoom(room);
			session.update(user);
			session.getTransaction().commit();
			session.close();
			return true;
			
		}catch(Exception exc) {
			log.debug(exc.getMessage());
			throw new CustomException("Room Already Exists or User already created a room(only one room per user)",HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Boolean deleteRoom(int id) {
		
		return null;
	}


	@Override
	public List<Room> findAll() {
		Session session = entityManager.unwrap(Session.class);
		List<Room> roomList = Tools.castList(Room.class,session.createQuery("from Room").getResultList());
		return roomList;
	}

}
