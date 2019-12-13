package com.john.api.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.john.api.model.UserInf;
import com.john.api.service.UserService;

/***
 * 
 * @author kpcdesktop
 * This is the user of the system who is stored in the database for purposes of API pulling and writing
 *
 */

@RestController

public class UsersController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<UserInf> viewAllUsers()
	{
		return userService.getUsers();
	}
	
	@PostMapping("/user")
	public UserInf addUser(@RequestBody UserInf newUser)
	{
		return userService.addUser(newUser);
		
	}
	
}
