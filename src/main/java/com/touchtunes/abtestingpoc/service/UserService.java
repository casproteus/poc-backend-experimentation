package com.touchtunes.abtestingpoc.service;

import com.touchtunes.abtestingpoc.entity.User;
import com.touchtunes.abtestingpoc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findUserById(Long userId) {
		return userRepository.findById(userId);
	}
}
