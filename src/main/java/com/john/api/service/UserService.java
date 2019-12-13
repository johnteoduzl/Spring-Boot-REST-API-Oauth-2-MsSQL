package com.john.api.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.john.api.model.UserInf;
import com.john.api.respository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	
	public UserInf addUser(UserInf addedUser)
	{
		addedUser.setPassword(new BCryptPasswordEncoder().encode(addedUser.getPassword()));
		return userRepo.save(addedUser);
	}
	
	public List<UserInf> getUsers()
	{
		return userRepo.findAll();
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserInf userInfor = userRepo.findByUserName(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfor.getRole());
		return (UserDetails) new User(userInfor.getUserName(),userInfor.getPassword(),Arrays.asList(authority));
	}
}
