package com.touchtunes.abtestingpoc.service;

import com.touchtunes.abtestingpoc.entity.Todo;
import com.touchtunes.abtestingpoc.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository){
		this.todoRepository = todoRepository;
	}

	public List<Todo> getTodosByUserId(Long userId) {
		return todoRepository.findAllByUserId(userId);
	}

	public Todo findById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}

	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public Todo updateTodo(Long id, Todo todo) {
		todo.setId(id);
		return todoRepository.save(todo);
	}

	public void deleteTodoById(Long id) {
		todoRepository.deleteById(id);
	}

}
