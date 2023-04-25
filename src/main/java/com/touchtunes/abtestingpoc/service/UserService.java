package com.touchtunes.abtestingpoc.service;

import com.touchtunes.abtestingpoc.entity.User;
import com.touchtunes.abtestingpoc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
	private UserRepository userRepository;
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	public User findUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
}
