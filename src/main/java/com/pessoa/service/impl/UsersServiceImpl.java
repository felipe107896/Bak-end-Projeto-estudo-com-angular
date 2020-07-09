package com.pessoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pessoa.model.User;
import com.pessoa.repository.UserRepository;
import com.pessoa.service.UsersService;

import lombok.experimental.var;


@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User findByUsernam(String username) {
		User userName = userRepository.findByUsername(username);
		if(userName != null) {
		 return userName;
		} else {
		throw new UsernameNotFoundException("Username"+username+ "not found");	
		}
	}

}
