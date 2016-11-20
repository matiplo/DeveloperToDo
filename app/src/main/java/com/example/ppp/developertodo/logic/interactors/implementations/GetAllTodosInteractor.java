package com.example.ppp.developertodo.logic.interactors.implementations;

import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.base.AbstractInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractorCallback;
import com.example.ppp.developertodo.logic.interactors.interfaces.IGetAllTodosInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

import java.util.List;

/**
 * Created by ppp on 2016-11-10.
 */

public class GetAllTodosInteractor extends AbstractInteractor implements IInteractor {

    private IGetAllTodosInteractorCallback mCallback;

    public GetAllTodosInteractor(IExecutor mThreadExecutor, IMainThread mMainThread, IGetAllTodosInteractorCallback mCallback, ITodoRepository mRepo) {
        super(mThreadExecutor, mMainThread, mRepo);
        this.mCallback=mCallback;
    }



    @Override
    public void run() {
        final List<Todo> todos=mRepo.getAllTodos();
        mMainThread.post(
                new Runnable() {
                    @Override
                    public void run() {
                        mCallback.OnTodosRetrieved(todos);
                    }
                }
        );
    }
}
