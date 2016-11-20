package com.example.ppp.developertodo.logic.interactors.interfaces;

import com.example.ppp.developertodo.logic.interactors.base.IInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public interface IGetAllTodosInteractorCallback extends IInteractorCallback {

    void OnTodosRetrieved(List<Todo> todos);
}
