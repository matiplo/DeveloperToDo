package com.example.ppp.developertodo.adapter.presenters.implementations;

import com.example.ppp.developertodo.adapter.presenters.base.AbstractPresenter;
import com.example.ppp.developertodo.adapter.presenters.interfaces.IAddEditPresenter;
import com.example.ppp.developertodo.implementation.ui.interfaces.IAddEditView;
import com.example.ppp.developertodo.implementation.ui.interfaces.IView;
import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.implementations.GetTodoByIdInteractor;
import com.example.ppp.developertodo.logic.interactors.implementations.SaveTodoInteractor;
import com.example.ppp.developertodo.logic.interactors.interfaces.IGetTodoByIdInteractorCallback;
import com.example.ppp.developertodo.logic.interactors.interfaces.ISaveTodoInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

/**
 * Created by ppp on 2016-11-12.
 */

public class AddEditPresenter extends AbstractPresenter implements IAddEditPresenter, ISaveTodoInteractorCallback, IGetTodoByIdInteractorCallback {

    private IAddEditView mView;

    public AddEditPresenter(IExecutor executor, IMainThread mainThread, IAddEditView view, ITodoRepository repo) {
        super(executor, mainThread, repo);
        this.mView=view;
    }



    @Override
    public void saveTodo(Todo todo) {
        SaveTodoInteractor interactor =  new SaveTodoInteractor(mExecutor, mMainThread, this, mRepo, todo);
        interactor.execute();
    }
    @Override
    public void onTodoSaved() {
        mView.onTodoSaved();
    }



    @Override
    public void getTodoById(int id) {
        GetTodoByIdInteractor interactor =  new GetTodoByIdInteractor(mExecutor, mMainThread, this, mRepo, id);
        interactor.execute();
    }
    @Override
    public void onTodoRetrieved(Todo todo) {
        mView.onTodoRetrieved(todo);
    }



    @Override
    public void start() {

    }





}
