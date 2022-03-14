package com.eftichiou.take3.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.eftichiou.take3.entity.User;
import com.eftichiou.take3.exceptions.CustomException;
import com.eftichiou.take3.rest.Controllers;

@Repository
public class UserDAO implements IUserDAO {

	private EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(Controllers.class);

	@Autowired
	public UserDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<User> findAll() {

		return null;
	}

	@Override
	public User findById(int id) {

		return null;
	}

	@Override
	public void save(User user) {

	}

	@Override
	public void deleteById(int id) {
		try {
			Session session = entityManager.unwrap(Session.class);
			User user = session.get(User.class, id);
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
			session.close();
		} catch (Exception exc) {
			throw new CustomException(exc);
		}

	}

	@Override
	public User findByEmail(String email) {

		return null;
	}

	@Override
	public void addUser(User user) {
		try {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(user);

		} catch (Exception exc) {
			log.debug(exc.getMessage());
			throw new CustomException("User Exists or Missing Information", HttpStatus.ALREADY_REPORTED);
		}

	}

	@Override
	public User loginUser(User user) {
		try {
			Session session = entityManager.unwrap(Session.class);
			Query<User> userByEmail = session.createQuery(
					"from User u where u.email='" + user.getEmail() + "' and u.password='" + user.getPassword() + "'",
					User.class);
			User theUser = userByEmail.getSingleResult();
			return theUser;
		} catch (Exception exc) {
			log.debug(exc.getMessage());
			throw new RuntimeException("Wrong Email or Password");
		}

	}
}
