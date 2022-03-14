package com.eftichiou.take3.rest;

import java.util.HashMap;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eftichiou.take3.dao.IRoomDAO;
import com.eftichiou.take3.dao.IUserDAO;
import com.eftichiou.take3.entity.Room;
import com.eftichiou.take3.entity.User;

@RestController
public class Controllers {
	private IUserDAO userDAO;

	@Autowired
	private IRoomDAO roomDAO;
	//	private static final Logger log = LoggerFactory.getLogger(Controllers.class);

	@Autowired
	public Controllers(IUserDAO iUserDAO) {
		userDAO = iUserDAO;
	}

	@GetMapping("/")
	public String sayHello() {

		return "can't";
	}

	@PostMapping("/add_user")
	public ResponseEntity<Object> addUser(@RequestBody User theUser) {
		userDAO.addUser(theUser);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "User with email " + theUser.getEmail() + " added");
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		User theUser = userDAO.loginUser(user);
		HashMap<String, String> json = new HashMap<>();
		json.put("email", theUser.getEmail());
		json.put("firstName", theUser.getFirstName());
		json.put("lastName", theUser.getLastName());
		json.put("token", "token");
		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}

	@PostMapping("/add_room")
	public ResponseEntity<Object> addRoom(@RequestBody Room room) {

		roomDAO.addRoom(room);

		HashMap<String, String> json = new HashMap<>();

		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}

	@GetMapping("/get_room_list")
	public List<Room> getRoomList() {
		return roomDAO.findAll();
	}

	@DeleteMapping("/delete_user")
	public ResponseEntity<Object> deleteUser(@RequestBody User user) {
		userDAO.deleteById(user.getId());
		HashMap<String, String> json = new HashMap<>();
		json.put("message", "deleted");
		return new ResponseEntity<Object>(json, HttpStatus.OK);

	}

}
