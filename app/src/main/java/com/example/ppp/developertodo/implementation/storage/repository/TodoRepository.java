package com.example.ppp.developertodo.implementation.storage.repository;

import com.example.ppp.developertodo.implementation.storage.converter.StorageConverter;
import com.example.ppp.developertodo.implementation.storage.model.TodoStorage;
import com.example.ppp.developertodo.implementation.storage.model.TodoStorage_Table;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public class TodoRepository implements ITodoRepository {

    @Override
    public List<Todo> getAllTodos() {

        List<TodoStorage> list = SQLite
                .select()
                .from(TodoStorage.class)
                .queryList();
        return StorageConverter.convertToLogicModel(list);

    }

    @Override
    public Todo getTodoById(int id) {
        TodoStorage todo = SQLite
                .select()
                .from(TodoStorage.class)
                .where(TodoStorage_Table.id.eq(id))
                .querySingle();

        return StorageConverter.convertToLogicModel(todo);
    }

    @Override
    public void insert(Todo item) {
        TodoStorage dbItem = StorageConverter.convertToStorageModel(item);

        dbItem.save();
    }

    @Override
    public void deleteTodosById(List<Integer> todos) {
        for (int index:todos
             ) {
            TodoStorage todo = SQLite
                    .select()
                    .from(TodoStorage.class)
                    .where(TodoStorage_Table.id.eq(index))
                    .querySingle();
            todo.delete();
        }
    }


    static {

        List<TodoStorage> todoList = SQLite.select()
                .from(TodoStorage.class)
                .queryList();

        // if the database is empty, let's add some items
        if (todoList.size() == 0) {

            try {
                TodoStorage todo = new TodoStorage("add main activity", 5);
                todo.insert();

                todo = new TodoStorage("implement intent service", 20);
                todo.insert();

                todo = new TodoStorage("debug and refactor WiFi p2p communication ", 60);
                todo.insert();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
