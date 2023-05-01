/*******************************************************************************
 * Copyright (C) 1997-2023 by TouchTunes Digital Jukebox, Inc. All Rights
 * Reserved.
 *
 * Disclosure or use in part or in whole without prior written consent
 * constitutes an infringement of copyright, punishable by law.
 ******************************************************************************/
package com.touchtunes.abtestingpoc.controller;

import com.touchtunes.abtestingpoc.entity.User;
import com.touchtunes.abtestingpoc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * List all users
	 *
	 * @return List of Users
	 */
	@GetMapping
	public List<User> listAllUsers() throws ChangeSetPersister.NotFoundException {
		log.debug("List all users");
		List<User> result = userService.listAllUsers();
		if (CollectionUtils.isEmpty(result)) {
			throw new ChangeSetPersister.NotFoundException();
		}
		log.info("Total {} users listed", result.size());
		return result;
	}

	/**
	 * Retrieve the user for a specific id
	 *
	 * @param id The unique id of the user
	 * @return User
	 */
	@GetMapping("{id}")
	public User getUserById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
		log.debug("Get user with id {}", id);
		Optional<User> optionalUser = userService.findUserById(id);
		User result = optionalUser.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
		log.info("Got user: {}", result);
		return result;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ChangeSetPersister.NotFoundException.class)
	public String handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
		return ex.getMessage();
	}
}

