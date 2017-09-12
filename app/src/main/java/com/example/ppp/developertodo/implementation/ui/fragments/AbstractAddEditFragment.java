package com.example.ppp.developertodo.implementation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.adapter.presenters.implementations.AddEditPresenter;
import com.example.ppp.developertodo.adapter.presenters.interfaces.IAddEditPresenter;
import com.example.ppp.developertodo.implementation.storage.repository.TodoRepository;
import com.example.ppp.developertodo.implementation.threading.MainThread;
import com.example.ppp.developertodo.implementation.ui.interfaces.IAddEditView;
import com.example.ppp.developertodo.implementation.ui.listeners.TextValidator;
import com.example.ppp.developertodo.logic.executors.ThreadExecutor;
import com.example.ppp.developertodo.logic.model.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public abstract class AbstractAddEditFragment extends Fragment implements IAddEditView {

    @BindView(R.id.estimated_time_edit_text)
    EditText estimatedTime;
    @BindView(R.id.new_task_edit_text)
    EditText newTask;
    @BindView(R.id.save_button)
    Button saveButton;
    private Unbinder unbinder;

    protected OnFragmentInteractionListener mListener;
    protected IAddEditPresenter mPresenter;

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    protected abstract void setupFields(Bundle bundle);

    protected abstract Todo buildTodoForSave();

    protected void setupFieldErrors() {

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


    private boolean isInputCorrect() {
        return newTask.getError() == null && estimatedTime.getError() == null;
    }

    public void onTodoSaved() {
        mListener.onActivityFinish();
    }

    public void onTodoRetrieved(Todo todo) {
        newTask.setText(todo.getName());
        estimatedTime.setText(Integer.toString(todo.getDuration()));
    }

    public interface OnFragmentInteractionListener {
        void onActivityFinish();

        int getIdFromIntent();
    }

    public AbstractAddEditFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        unbinder=ButterKnife.bind(this,view);
        return view;
    }

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


    protected int getLayoutResourceId() {
        return R.layout.fragment_add_edit;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void showMesssage(int stringId) {
        Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT).show();
    }



}
