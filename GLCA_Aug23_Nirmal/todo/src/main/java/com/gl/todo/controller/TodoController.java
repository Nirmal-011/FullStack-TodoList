package com.gl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.todo.dto.TodoDTO;
import com.gl.todo.service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO){
		TodoDTO todoDto=todoService.createTodo(todoDTO);
		return new ResponseEntity<TodoDTO>(todoDto,HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") int id){
		TodoDTO todo=todoService.getTodoById(id);
		return new ResponseEntity<TodoDTO> (todo,HttpStatus.OK);
	}
	
	@PutMapping("{id}")	
	public ResponseEntity<TodoDTO> updateTOdo(@PathVariable("id") int id,@RequestBody TodoDTO todoDTO){
		TodoDTO todo=todoService.updateTodo(id, todoDTO);
		return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteTodoById(@PathVariable("id")int id){
		todoService.deleteTodo(id);
		return new ResponseEntity<String>("Todo Deleted Successfully",HttpStatus.OK); 
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDTO>> getAllTodos(){
		List<TodoDTO> todo=todoService.getAllTodo();
		return new ResponseEntity<List<TodoDTO>>(todo,HttpStatus.OK);
	}
	
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDTO> completedTask(@PathVariable("id")int id){
		TodoDTO todo=todoService.todoComplete(id);
		return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
	}
	
	@PatchMapping("{id}/incomplete")
	public ResponseEntity<TodoDTO> inComplete(@PathVariable("id")int id){
		TodoDTO todo=todoService.todoInComplete(id);
		return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
	}
	
}
