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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Retrieve the user for a specific id
	 *
	 * @param id
	 *           The unique id of the user
	 * @return User
	 */
	@GetMapping("{id}")
	public User getStatusForJukebox(
			@PathVariable Long id) {

		log.debug("Get user with id {}", id);
		User result = userService.findUserById(id);
		log.info("Got user: {}", result);

		return result;
	}


}
