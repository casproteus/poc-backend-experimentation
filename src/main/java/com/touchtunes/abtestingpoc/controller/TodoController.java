/*******************************************************************************
 * Copyright (C) 1997-2023 by TouchTunes Digital Jukebox, Inc. All Rights
 * Reserved.
 *
 * Disclosure or use in part or in whole without prior written consent
 * constitutes an infringement of copyright, punishable by law.
 ******************************************************************************/
package com.touchtunes.abtestingpoc.controller;

import com.touchtunes.abtestingpoc.entity.Todo;
import com.touchtunes.abtestingpoc.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/todo")
@Slf4j
public class TodoController {

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
	public List<Todo> getTodosByUserId( @PathVariable Long userId) {

		log.debug("Get Todos of useId - {}, fields - {}", userId);
		List<Todo> result = todoService.getTodosByUserId(userId);
		log.info("Got todos: {}", result);

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
	public Todo getTodoById( @PathVariable Long id) {

		log.debug("Get Todo of id - {}", id);
		Todo result = todoService.findById(id);
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

		return todo;
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
	public Todo updateTodoById(@PathVariable Long id, @RequestBody Todo todo) {
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
	public void deleteTodoById(@PathVariable Long id) {
		log.debug("delete todo with id - {}", id);
		todoService.deleteTodoById(id);
		log.info("deleted todo: {}", id);
	}

}
