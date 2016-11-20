package com.example.ppp.developertodo.implementation.storage.converter;

import com.example.ppp.developertodo.implementation.storage.model.TodoStorage;
import com.example.ppp.developertodo.logic.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public class StorageConverter {

    public static Todo convertToLogicModel(TodoStorage item) {
        Todo todo = new Todo(item.getName(), item.getDuration());
        todo.setId(item.getId());
        return todo;
    }

    public static TodoStorage convertToStorageModel(Todo item) {
        return new TodoStorage(item.getId(), item.getName(), item.getDuration());
    }

    public static List<Todo> convertToLogicModel(List<TodoStorage> list) {
        List<Todo> converted = new ArrayList<Todo>();
        for (TodoStorage item : list
                ) {
            converted.add(convertToLogicModel(item));

        }

        list.clear();
        list=null;
        return converted;
    }
}
