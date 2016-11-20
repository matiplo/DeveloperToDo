package com.example.ppp.developertodo.logic.interactors.interfaces;

import com.example.ppp.developertodo.logic.interactors.base.IInteractorCallback;

/**
 * Created by ppp on 2016-11-15.
 */

public interface IDeleteTodosByIdInteractorCallback extends IInteractorCallback {
    void onTodosDeleted();
}
