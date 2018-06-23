package com.docker.container.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.docker.container.repo.UserRepo;

/**
 * @author atwa Jun 19, 2018
 */
@Configuration
public class ResourceServerConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepo userRepo;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().authorizeRequests().antMatchers("/api/home").permitAll()
				.antMatchers("/api/users/**").authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return new CustomUserDetails(userRepo.findByUsername(username));
			}
		}).passwordEncoder(passwordEncoder());

	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
