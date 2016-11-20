package com.example.ppp.developertodo.adapter.presenters.implementations;

import com.example.ppp.developertodo.adapter.presenters.base.AbstractPresenter;
import com.example.ppp.developertodo.adapter.presenters.interfaces.IMainPresenter;
import com.example.ppp.developertodo.implementation.ui.interfaces.IMainView;
import com.example.ppp.developertodo.implementation.ui.interfaces.IView;
import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.implementations.DeleteTodosByIdInteractor;
import com.example.ppp.developertodo.logic.interactors.implementations.GetAllTodosInteractor;
import com.example.ppp.developertodo.logic.interactors.interfaces.IDeleteTodosByIdInteractorCallback;
import com.example.ppp.developertodo.logic.interactors.interfaces.IGetAllTodosInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public class MainPresenter extends AbstractPresenter implements IMainPresenter, IGetAllTodosInteractorCallback, IDeleteTodosByIdInteractorCallback {

    private IMainView mView;

    public MainPresenter(IExecutor executor, IMainThread mainThread, ITodoRepository repo, IMainView view) {
        super(executor, mainThread, repo);
        mView=view;
    }

    @Override
    public void start() {
        getAllTodos();
    }




    @Override
    public void getAllTodos() {
        GetAllTodosInteractor interactor =  new GetAllTodosInteractor(mExecutor, mMainThread, this, mRepo);
        interactor.execute();
    }
    @Override
    public void OnTodosRetrieved(List<Todo> todos) {
        mView.showTodos(todos);
    }




    @Override
    public void deleteTodosById(List<Integer> todoList) {
        DeleteTodosByIdInteractor interactor=new DeleteTodosByIdInteractor(mExecutor,mMainThread,this,mRepo,todoList);
        interactor.execute();
    }
    @Override
    public void onTodosDeleted() {
        mView.onTodosDeled();
    }
}
