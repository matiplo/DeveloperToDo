package com.example.ppp.developertodo.implementation.ui.interfaces;

import com.example.ppp.developertodo.logic.model.Todo;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public interface IMainView extends IView {
    void showTodos(List<Todo> todoList);

    void onClickAdd();

    void onClickEdit(int id);

    void onClickDelete(List<Integer> list);

    void onClickDownload();

    void onTodosDeled();

    void onTodoAdded();

    void onTodoEdited();

    void onTodoDownloaded(Todo todo);



}
