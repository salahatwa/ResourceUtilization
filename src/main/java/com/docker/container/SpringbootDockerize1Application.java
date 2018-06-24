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
/**
 * http://localhost:8085/oauth/token?grant_type=password&username=atwa&password=salah
 * http://localhost:8085/api/home
 * http://localhost:8085/api/user/me?access_token=dd9f8111-e8d3-4243-9d6f-13b62511ba37
 * 
 * @author atwa Jun 23, 2018
 */
@SpringBootApplication
public class SpringbootDockerize1Application {

	@Autowired
	private PasswordEncoder encoder;

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
		user.setPassword(encoder.encode("salah"));
		user.setRoles(Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
		user.setAge(50);

		userService.save(user);
	}
	// 7cd4dc6c-d173-4490-a221-cd3d607ac12b atwa
	// dbe93227-617b-467e-b096-bde39acfd296 russia

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
