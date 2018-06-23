package com.docker.container.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.container.entities.User;
import com.docker.container.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	 @RequestMapping("/user/me")
	    public Principal user(Principal principal) {
		System.out.println(principal);
	        return principal;
	    }

}
