package com.example.ppp.developertodo.logic.repository;

import com.example.ppp.developertodo.logic.model.Todo;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public interface ITodoRepository {

    List<Todo> getAllTodos();
    Todo getTodoById(int id);
    void insert(Todo todo);
    void deleteTodosById(List<Integer> todos);

}
