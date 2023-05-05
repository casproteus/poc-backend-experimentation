/*******************************************************************************
 * Copyright (C) 1997-2023 by TouchTunes Digital Jukebox, Inc. All Rights
 * Reserved.
 *
 * Disclosure or use in part or in whole without prior written consent
 * constitutes an infringement of copyright, punishable by law.
 ******************************************************************************/
package com.touchtunes.abtestingpoc.controller;

import com.abtasty.flagship.main.Flagship;
import com.touchtunes.abtestingpoc.entity.Todo;
import com.touchtunes.abtestingpoc.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todo")
@Slf4j
public class TodoController {

	public static final String VISITOR_UNIQUE_ID = "Visitor-Unique-Id";
	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	/**
	 * Retrieve the Todos for a specific user
	 *
	 * @param userId
	 *           The unique id of the user
	 * @return List of Todo
	 */
	@GetMapping("{userId}/todos")
	public List<Todo> listTodosByUserId( @PathVariable Long userId) throws ChangeSetPersister.NotFoundException {

		log.debug("Get Todos of useId - {}, fields - {}", userId);
		List<Todo> result = todoService.getTodosByUserId(userId);
		log.info("Got todos: {}", result);
		if (CollectionUtils.isEmpty(result)) {
			throw new ChangeSetPersister.NotFoundException();
		}
		return result;
	}

	/**
	 * Retrieve a Todo by id
	 *
	 * @param id
	 *           The unique id of the todo
	 * @return todo
	 */
	@GetMapping("{id}")
	public Todo getTodoById(
			@PathVariable Long id,
			@RequestHeader(VISITOR_UNIQUE_ID) String visitorUniqueId)
			throws ChangeSetPersister.NotFoundException {

		log.debug("Get Todo of id - {}", id);
		Todo result = todoService.findById(id, visitorUniqueId)
				.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
		log.info("Got todo: {}", result);

		return result;
	}

	/**
	 * Create a todo
	 *
	 * @param todo
	 *           The todo to create
	 * @return todo with id
	 */
	@PostMapping
	public Todo addTodo(@RequestBody Todo todo) {

		log.debug("create a todo like - {}", todo);
		Todo result = todoService.createTodo(todo);
		log.info("created todo: {}", result);

		return result;
	}

	/**
	 * update a Todo
	 *
	 * @param id
	 *           The id of todo which will be updated
	 * @param todo
	 *           will update to like what
	 * @return todo updated todo
	 */
	@PutMapping("{id}")
	public Todo updateTodoById(
			@PathVariable Long id,
			@RequestBody Todo todo,
			@RequestHeader(VISITOR_UNIQUE_ID) String visitorUniqueId)
			throws ChangeSetPersister.NotFoundException {
		log.debug("check if todo exists - {}", id);
		todoService.findById(id, visitorUniqueId)
				.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
		log.debug("todo exists!");

		log.debug("update Todo {} to - {}", id, todo);
		Todo result = todoService.updateTodo(id, todo);
		log.info("Todo updated to: {}", result);

		return result;
	}

	/**
	 * delete a Todo with specific id
	 *
	 * @param id
	 *           The unique id of the Todo
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodoById(@PathVariable Long id,
			@RequestHeader(VISITOR_UNIQUE_ID) String visitorUniqueId)
			throws ChangeSetPersister.NotFoundException {
		log.debug("check if todo exists - {}", id);
		todoService.findById(id, visitorUniqueId)
				.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
		log.debug("todo exists!");

		log.debug("delete todo with id - {}", id);
		todoService.deleteTodoById(id);
		log.info("deleted todo: {}", id);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ChangeSetPersister.NotFoundException.class)
	public String handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
		return ex.getMessage();
	}
}