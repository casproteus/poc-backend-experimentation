/*******************************************************************************
 * Copyright (C) 1997-2023 by TouchTunes Digital Jukebox, Inc. All Rights
 * Reserved.
 *
 * Disclosure or use in part or in whole without prior written consent
 * constitutes an infringement of copyright, punishable by law.
 ******************************************************************************/
package com.touchtunes.abtestingpoc.controller;

import com.touchtunes.abtestingpoc.dto.UserDTO;
import com.touchtunes.abtestingpoc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
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
	 * Retrieve the user for a specific userId
	 *
	 * @param userId
	 *           The unique id of the user
	 * @return UserDTO
	 */
	@GetMapping("{userId}")
	public UserDTO getStatusForJukebox(
			@PathVariable(name = "userId") String userId) {

		log.debug("Get user with id {}", userId);
		UserDTO result = userService.getUser(userId);
		log.info("Got user: {}", result);

		return result;
	}


}
