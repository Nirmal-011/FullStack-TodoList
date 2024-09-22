package com.gl.todo.mapper;

import com.gl.todo.dto.TodoDTO;
import com.gl.todo.entity.Todo;

public class TodoMapper {

	public static TodoDTO mapToDTO(Todo todo) {
		return new TodoDTO(
				todo.getId(),
				todo.getTitle(),
				todo.getDescription(),
				todo.isTaskCompleted()
				);	
	}
	public static Todo mapToEntity(TodoDTO todoDTO) {
		return new Todo(
				todoDTO.getId(),
				todoDTO.getTitle(),
				todoDTO.getDescription(),
				todoDTO.isTaskCompleted()
				);
	}
}
