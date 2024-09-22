package com.gl.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todo.Repository.TodoRepository;
import com.gl.todo.dto.TodoDTO;
import com.gl.todo.entity.Todo;
import com.gl.todo.exception.ResourceNotFoundException;
import com.gl.todo.mapper.TodoMapper;
import com.gl.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public TodoDTO createTodo(TodoDTO todoDTO) {
		Todo todo=TodoMapper.mapToEntity(todoDTO);
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToDTO(savedTodo);
	}

	@Override
	public TodoDTO getTodoById(int id) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo with id"+id+"is not present"));
		return TodoMapper.mapToDTO(todo);
	}

	@Override
	public TodoDTO updateTodo(int id, TodoDTO todoDTO) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo with id"+id+"is not present"));
		todo.setTitle(todoDTO.getTitle());
		todo.setDescription(todoDTO.getDescription());
		todo.setTaskCompleted(todoDTO.isTaskCompleted());
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToDTO(savedTodo);
	}

	@Override
	public void deleteTodo(int id) {
		todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo with id"+id+"is not prensent"));
		todoRepository.deleteById(id);
	}

	@Override
	public List<TodoDTO> getAllTodo() {
		List<Todo>todos=todoRepository.findAll();
		return todos.stream().map((todo)->TodoMapper.mapToDTO(todo)).collect(Collectors.toList());
	}

	@Override
	public TodoDTO todoComplete(int id) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo with id"+id+"is not present"));
		todo.setTaskCompleted(true);
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToDTO(savedTodo);
	}

	@Override
	public TodoDTO todoInComplete(int id) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo with id"+id+"is not present"));
		todo.setTaskCompleted(false);
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToDTO(savedTodo);
	}

}
