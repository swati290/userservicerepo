package com.springboot.userservice.userservice.service;

import java.util.List;
import java.util.Optional;

import com.springboot.userservice.userservice.entity.UserData;
import com.springboot.userservice.userservice.exception.UserNotFoundException;

public interface UserService {

	public UserData saveUser(UserData userdata);

	public Optional<UserData> findById(long id) throws UserNotFoundException;




	public void modifyUserData(long userId, UserData userdata);

	public void deleteUserById(long userId) throws UserNotFoundException;

	public Optional<UserData> getUserByUserName(String username) throws UserNotFoundException;

	public List<UserData> findAllUsers();



}
