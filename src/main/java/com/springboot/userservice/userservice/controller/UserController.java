package com.springboot.userservice.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.userservice.userservice.entity.UserData;
import com.springboot.userservice.userservice.exception.UserNotFoundException;
import com.springboot.userservice.userservice.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	// Save User

	@PostMapping("/user")
	public ResponseEntity<UserData> saveUser(@RequestBody UserData userdata) {
		//http://localhost:8086/api/user
		LOGGER.info("Inside saveUser of UserController");

		UserData newUserData = userService.saveUser(userdata);
		if (userdata != null) {
			return new ResponseEntity<UserData>(newUserData, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<UserData>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//update user
	@PutMapping(path = "/updateUser")
	public ResponseEntity<UserData> updateUserData(@RequestBody UserData userdata) {
		//http://localhost:8086/api/updateUser

		LOGGER.info("Inside updateUser of UserController");

		ResponseEntity<UserData> response = null;

		userService.modifyUserData(userdata.getUserId(), userdata);

		return new ResponseEntity<>(userdata, HttpStatus.OK);
	}
	
	//get user by Id
	@GetMapping("/id/{id}")
	public ResponseEntity<UserData> getUserById(@PathVariable("id") long id) throws UserNotFoundException {
      //http://localhost:8086/api/id/1
		LOGGER.info("Inside getUserById of UserController");

		Optional<UserData> userData = userService.findById(id);

		if (userData.isPresent()) {
			return new

			ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
    //get user by username
	@GetMapping("/username/{username}")
	public ResponseEntity<UserData> getUserByUserName(@PathVariable("username") String username) throws UserNotFoundException {
		//http://localhost:8086/api/username/teja
		LOGGER.info("Inside getUserByUserName of UserController");

		Optional<UserData> userData = userService.getUserByUserName(username);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	// delete user by id

		@DeleteMapping("/id/{id}")

		public ResponseEntity<?> deleteUserById(@PathVariable("id") long userId) throws UserNotFoundException {
			//http://localhost:8086/api/id/3

			LOGGER.info("Inside deleteUserById of UserController");

			userService.deleteUserById(userId);

			return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
		}

	
		//get all users
		@GetMapping("/getUsers")
		public ResponseEntity<List<UserData>> findAllUsers() {
			//http://localhost:8086/api/getUsers
	
			LOGGER.info("Inside findAllUsers of UserController");

		  List<UserData> userdata = userService.findAllUsers();
			
			return new ResponseEntity<>(userdata, HttpStatus.OK);
		}

}
