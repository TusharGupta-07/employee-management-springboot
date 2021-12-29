package com.example.hackathon.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hackathon.Model.Role;
import com.example.hackathon.Model.User;
import com.example.hackathon.Repository.UserRepository;
import com.example.hackathon.Web.Dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegistrationDto registrationDto){
		// TODO Auto-generated method stub
		
		
		
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), 
				registrationDto.getEmail(), 
				passwordEncoder.encode(registrationDto.getPassword()), 
				Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}
	
	

}
