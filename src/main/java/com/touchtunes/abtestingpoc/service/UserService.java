package com.touchtunes.abtestingpoc.service;

import com.abtasty.flagship.main.Flagship;
import com.touchtunes.abtestingpoc.entity.User;
import com.touchtunes.abtestingpoc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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

	public Optional<User> findUserById(Long userId) throws ExecutionException, InterruptedException {
		return userRepository.findById(userId);
	}
}
