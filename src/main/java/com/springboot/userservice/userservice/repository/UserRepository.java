package com.springboot.userservice.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.userservice.userservice.entity.UserData;


public interface UserRepository extends JpaRepository<UserData, Long> {


	Optional<UserData> getUserByUserName(String username);


}
