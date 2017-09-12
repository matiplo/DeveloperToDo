package com.example.ppp.developertodo.implementation.ui.fragments;

import android.os.Bundle;

import com.example.ppp.developertodo.logic.model.Todo;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFragment extends AbstractAddEditFragment {

    @Override
    protected void setupFields(Bundle bundle) {
        setupFieldErrors();

    }

    @Override
    protected Todo buildTodoForSave() {
        Todo todo = new Todo(newTask.getText().toString(), Integer.parseInt(estimatedTime.getText().toString()));
        return todo;
    }

}
