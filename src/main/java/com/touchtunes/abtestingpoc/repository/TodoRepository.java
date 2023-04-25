package com.touchtunes.abtestingpoc.repository;

import com.touchtunes.abtestingpoc.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findAllByUserId(Long userId);
}
