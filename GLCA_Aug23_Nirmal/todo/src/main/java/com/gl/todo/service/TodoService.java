package com.gl.todo.service;

import java.util.List;

import com.gl.todo.dto.TodoDTO;

public interface TodoService {
	
	TodoDTO createTodo(TodoDTO todoDTO);

	TodoDTO getTodoById(int id);

	TodoDTO updateTodo(int id,TodoDTO todoDTO);

	void deleteTodo(int id);

	List<TodoDTO> getAllTodo();

	TodoDTO todoComplete(int id);

	TodoDTO todoInComplete(int id);

}
