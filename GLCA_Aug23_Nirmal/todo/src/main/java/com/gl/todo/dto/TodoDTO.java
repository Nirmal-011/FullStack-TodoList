package com.gl.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	private int id;
	
	private String title;
	
	private String description;
	
	private boolean taskCompleted;

}
