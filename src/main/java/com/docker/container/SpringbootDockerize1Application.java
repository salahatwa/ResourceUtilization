package com.docker.container;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.docker.container.entities.Role;
import com.docker.container.entities.User;
import com.docker.container.service.UserService;

//cross site scripting
@SpringBootApplication
public class SpringbootDockerize1Application {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDockerize1Application.class, args);

	}

	// @Autowired
	/**
	 * http://localhost:8085/oauth/token?grant_type=password&username=atwa&password=salah
	 */
	public void insertUser() {
		User user = new User();
		user.setUsername("atwa");
		user.setPassword(passwordEncoder.encode("salah"));
		user.setRoles(Arrays.asList(new Role("USER"), new Role("ACTUATOR")));

		userService.save(user);
	}

	// http://localhost:8085/oauth/token?grant_type=password&username=ssss&password=salah

	/*
	 * @Autowired public void authenticationManager(AuthenticationManagerBuilder
	 * builder, UserRepo repo) throws Exception { builder.userDetailsService(new
	 * UserDetailsService() {
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { return new
	 * CustomUserDetails(repo.findByUsername(username)); } }); }
	 */

}
