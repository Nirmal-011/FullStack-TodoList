package com.gl.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
