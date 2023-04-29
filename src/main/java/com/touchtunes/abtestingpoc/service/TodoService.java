package com.touchtunes.abtestingpoc.service;

import com.touchtunes.abtestingpoc.entity.Todo;
import com.touchtunes.abtestingpoc.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Todo> findById(Long id) {
		return todoRepository.findById(id);
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
