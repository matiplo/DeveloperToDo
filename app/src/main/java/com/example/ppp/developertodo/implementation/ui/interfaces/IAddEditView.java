package com.example.ppp.developertodo.implementation.ui.interfaces;


import com.example.ppp.developertodo.logic.model.Todo;

/**
 * Created by ppp on 2016-11-12.
 */

public interface IAddEditView extends IView {
    void onTodoSaved();
    void onTodoRetrieved(Todo todo);
}
