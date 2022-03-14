package com.eftichiou.take3.dao;


import java.util.List;

import com.eftichiou.take3.entity.User;

public interface IUserDAO {

	public List<User> findAll();
	public User findById(int id);
	public void save(User user);
	public void deleteById(int id);
	public User findByEmail(String email);
	public void addUser(User user);
	public User loginUser(User user);
}
