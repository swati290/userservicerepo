package com.springboot.userservice.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.userservice.userservice.entity.UserData;
import com.springboot.userservice.userservice.exception.UserNotFoundException;
import com.springboot.userservice.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserRepository userRepository;

    //save user
	@Override
	public UserData saveUser(UserData userdata) {
		
		return userRepository.save(userdata);
	}

	//get user by id
	@Override
	public Optional<UserData> findById(long id) throws UserNotFoundException {
		Optional<UserData> userdata = userRepository.findById(id);

		if (!userdata.isPresent()) {

			// Throw user not present 
			throw new
			UserNotFoundException("user not present ");
		}

		return userdata;

	}

	//get user by username
		@Override
		public Optional<UserData> getUserByUserName(String username) throws UserNotFoundException {
			
			
			
			Optional<UserData> userdata = userRepository.getUserByUserName(username);

			if (!userdata.isPresent()) {

				// Throw user not present 
				throw new
				UserNotFoundException("user not present ");
			}

			return userdata;

		}

			
		
	
	

	
	
	//update user by id
	@Override
	public void modifyUserData(long userId, UserData userdata) {

	UserData u = userRepository.findById(userId).get();
		u = userdata;

		userRepository.save(u);
	}
    
	//delete user by id
	@Override
	public void deleteUserById(long userId) throws UserNotFoundException {
		Optional<UserData> userdata = userRepository.findById(userId);
		if (!userdata.isPresent()) {

			// Throw Advertisement not available 
			throw new
			UserNotFoundException("user not present");
		}
		userRepository.deleteById(userId);

	}

	

	    
	//get all users
	@Override
	public List<UserData> findAllUsers() {
		List<UserData> list = userRepository.findAll();
		
		return userRepository.findAll();
	}

	
}
