package com.example.ppp.developertodo.implementation.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ppp.developertodo.logic.model.Todo;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends AbstractAddEditFragment {


    @Override
    protected void setupFields(Bundle bundle) {
        setupFieldErrors();
        //take use of the fact that Bundle is null only at initial onCreate and not null during configuration changes
        if (bundle == null) {
            mPresenter.getTodoById(mListener.getIdFromIntent());
        }
    }


    @Override
    protected Todo buildTodoForSave() {
        Todo todo = new Todo(newTask.getText().toString(), Integer.parseInt(estimatedTime.getText().toString()));

        todo.setId(mListener.getIdFromIntent());


        return todo;
    }
}
