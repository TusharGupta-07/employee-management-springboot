package com.example.hackathon.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.hackathon.Model.User;
import com.example.hackathon.Web.Dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	
	User save(UserRegistrationDto registrationDto);

	User findByEmail(String email);


}
