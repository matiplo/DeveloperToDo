package com.example.ppp.developertodo.adapter.presenters.interfaces;

import com.example.ppp.developertodo.adapter.presenters.base.IPresenter;
import com.example.ppp.developertodo.logic.model.Todo;

/**
 * Created by ppp on 2016-11-12.
 */

public interface IAddEditPresenter extends IPresenter {
    void saveTodo(Todo todo);

    void getTodoById(int id);
}
