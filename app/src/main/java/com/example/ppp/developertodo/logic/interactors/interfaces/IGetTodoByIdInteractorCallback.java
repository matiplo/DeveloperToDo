package com.example.ppp.developertodo.logic.interactors.interfaces;

import com.example.ppp.developertodo.logic.interactors.base.IInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;

/**
 * Created by ppp on 2016-11-15.
 */

public interface IGetTodoByIdInteractorCallback extends IInteractorCallback {
    void onTodoRetrieved(Todo todo);
}
