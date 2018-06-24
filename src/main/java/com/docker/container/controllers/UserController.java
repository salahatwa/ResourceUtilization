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

	@GetMapping(value = "/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * To Get Current LoggedIn User
	 * http://localhost:8085/api/user/me?access_token=dd9f8111-e8d3-4243-9d6f-13b62511ba37
	 * 
	 * @param principal
	 * @return
	 */
	@GetMapping("/user/me")
	public Principal user(Principal principal) {

		System.out.println(principal);
		
		return principal;
	}

}
