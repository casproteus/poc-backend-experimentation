package com.touchtunes.abtestingpoc.service;

import com.touchtunes.abtestingpoc.entity.Todo;
import com.touchtunes.abtestingpoc.repository.TodoRepository;
import com.touchtunes.abtestingpoc.service.abtest.ABTestSolution;
import com.touchtunes.abtestingpoc.service.abtest.Mammal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
public class TodoService {

	public static final String MARK = "***";
	private TodoRepository todoRepository;

	private ABTestSolution abTestSolution;

	public TodoService(TodoRepository todoRepository, ABTestSolution abTestSolution){
		this.todoRepository = todoRepository;
		this.abTestSolution = abTestSolution;
	}
	public List<Todo> getTodosByUserId(Long userId) {
		return todoRepository.findAllByUserId(userId);
	}

	public Optional<Todo> findById(Long id, String visitorUniqueId) {
		Optional<Todo> optionalTodo = todoRepository.findById(id);

		if (!optionalTodo.isPresent() || !abTestSolution.isMammalFilter(visitorUniqueId)) {
			return optionalTodo;
		}
		Todo todo = optionalTodo.get();
		String title = todo.getTitle();
		String description = todo.getDescription();

		for (Mammal mammal : Mammal.values()) {
			String mammalName = mammal.name();
			title = title.replaceAll("(?i)" + Pattern.quote(mammalName), MARK);
			description = description.replaceAll("(?i)" + Pattern.quote(mammalName), MARK);
		}

		todo.setTitle(title);
		todo.setDescription(description);

		return Optional.of(todo);
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
