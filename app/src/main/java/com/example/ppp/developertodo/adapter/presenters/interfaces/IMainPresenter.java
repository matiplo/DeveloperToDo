package com.example.ppp.developertodo.adapter.presenters.interfaces;

import com.example.ppp.developertodo.adapter.presenters.base.IPresenter;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public interface IMainPresenter extends IPresenter {

    void getAllTodos();

    void deleteTodosById(List<Integer> todoList);

}
