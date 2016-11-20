package com.example.ppp.developertodo.implementation.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.adapter.presenters.implementations.AddEditPresenter;
import com.example.ppp.developertodo.adapter.presenters.implementations.MainPresenter;
import com.example.ppp.developertodo.adapter.presenters.interfaces.IAddEditPresenter;
import com.example.ppp.developertodo.implementation.storage.repository.TodoRepository;
import com.example.ppp.developertodo.implementation.threading.MainThread;
import com.example.ppp.developertodo.implementation.ui.activities.MainActivity;
import com.example.ppp.developertodo.implementation.ui.interfaces.IAddEditView;
import com.example.ppp.developertodo.implementation.ui.listeners.TextValidator;
import com.example.ppp.developertodo.logic.executors.ThreadExecutor;
import com.example.ppp.developertodo.logic.model.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddEditFragment extends AbstractBaseFragment implements IAddEditView {

    private IAddEditPresenter mPresenter;

    @BindView(R.id.estimated_time_edit_text)
    EditText estimatedTime;

    @BindView(R.id.new_task_edit_text)
    EditText newTask;

    @BindView(R.id.save_button)
    Button saveButton;

    @OnClick(R.id.save_button)
    public void save(Button button) {
        processSave();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new AddEditPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new TodoRepository());

        setupFields(savedInstanceState);
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_add_edit;
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }


    private void setupFields(Bundle bundle) {
        setupFieldErrors();

        //take use of the fact that Bundle is null only at initial onCreate and not null during configuration changes
        if (mListener.isEditMode() && bundle == null) {
            mPresenter.getTodoById(mListener.getIdFromIntent());
        }
    }

    private void setupFieldErrors(){

        newTask.setError(getString(R.string.error_fill_in));
        estimatedTime.setError(getString(R.string.error_fill_in));

        newTask.addTextChangedListener(new TextValidator() {
            @Override
            public void validate(String text) {
                if (text.trim().isEmpty())
                    newTask.setError(getString(R.string.error_fill_in));
                else newTask.setError(null);

            }
        });

        estimatedTime.addTextChangedListener(new TextValidator() {
            @Override
            public void validate(String text) {
                if (text.trim().isEmpty())
                    estimatedTime.setError(getString(R.string.error_fill_in));
                else if (!tryParseInt(text.trim().toString())) {
                    estimatedTime.setError(getString(R.string.error_too_big_number));
                } else estimatedTime.setError(null);
            }
        });
    }



    private void processSave() {
        if (!isInputCorrect()) {
            showMesssage(R.string.error_input);
            return;
        }
        mPresenter.saveTodo(buildTodoForSave());
    }

    private Todo buildTodoForSave() {
        Todo todo = new Todo(newTask.getText().toString(), Integer.parseInt(estimatedTime.getText().toString()));
        if (mListener.isEditMode()) {
            todo.setId(mListener.getIdFromIntent());
        }

        return todo;
    }

    private boolean isInputCorrect() {
        return newTask.getError() == null && estimatedTime.getError() == null;
    }




    @Override
    public void onTodoSaved() {
        mListener.onActivityFinish();
    }

    @Override
    public void onTodoRetrieved(Todo todo) {
        newTask.setText(todo.getName());
        estimatedTime.setText(Integer.toString(todo.getDuration()));
    }

}
