package com.john.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.api.model.User;
import com.john.api.respository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User addUser(User addedUser)
	{
		//addedUser.setPassword(/*new BCryptPasswordEncoder().encode(addedUser.getPassword())*/);
		return userRepo.save(addedUser);
	}
	
	public List<User> getUsers()
	{
		return userRepo.findAll();
	}
}
