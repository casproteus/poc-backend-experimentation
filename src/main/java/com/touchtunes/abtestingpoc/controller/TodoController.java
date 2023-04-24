/*******************************************************************************
 * Copyright (C) 1997-2023 by TouchTunes Digital Jukebox, Inc. All Rights
 * Reserved.
 *
 * Disclosure or use in part or in whole without prior written consent
 * constitutes an infringement of copyright, punishable by law.
 ******************************************************************************/
package com.touchtunes.abtestingpoc.controller;

import com.touchtunes.abtestingpoc.dto.TodoDTO;
import com.touchtunes.abtestingpoc.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 * @return TodoDTO
	 */
	@GetMapping("{userId}")
	public List<TodoDTO> getTodos( @PathVariable(name = "userId") String userId) {

		log.debug("Get Todos of useId - {}, fields - {}", userId);
		List<TodoDTO> result = todoService.getTodos(userId);
		log.info("Got todos: {}", result);

		return result;
	}

	/**
	 * Create a todo with given TodoDTO
	 *
	 * @param todoDTO
	 *           The todo to create
	 * @return TodoDTO with id
	 */
	@PostMapping
	public TodoDTO createTodos(TodoDTO todoDTO) {

		log.debug("create Todos of useId - {}, todo - {}", todoDTO);
		TodoDTO result = todoService.createTodo( todoDTO);
		log.info("Got todos: {}", result);

		return todoDTO;
	}

	/**
	 * update a Todo with given TodoDto
	 *
	 * @param todoDTO
	 *           The given TODO which will update to
	 */
	@PutMapping
	public void updateTodo(TodoDTO todoDTO) {
		log.debug("update Todo to - {}", todoDTO);
		todoService.updateTodo(todoDTO);
		log.info("Todo updated");
	}

	/**
	 * delete a Todo with specific id
	 *
	 * @param todoId
	 *           The unique id of the Todo
	 */
	@DeleteMapping
	public void deleteTodo(String todoId) {
		log.debug("delete Todo of id - {}", todoId);
		todoService.deleteTodo(todoId);
		log.info("deleted todo: {}", todoId);
	}

}
