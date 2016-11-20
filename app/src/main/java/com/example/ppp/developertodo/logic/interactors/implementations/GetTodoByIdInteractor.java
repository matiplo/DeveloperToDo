package com.example.ppp.developertodo.logic.interactors.implementations;

import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.base.AbstractInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractor;
import com.example.ppp.developertodo.logic.interactors.interfaces.IGetTodoByIdInteractorCallback;
import com.example.ppp.developertodo.logic.interactors.interfaces.ISaveTodoInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

/**
 * Created by ppp on 2016-11-15.
 */

public class GetTodoByIdInteractor extends AbstractInteractor implements IInteractor {

    private IGetTodoByIdInteractorCallback mCallback;
    private int id;

    public GetTodoByIdInteractor(IExecutor mThreadExecutor, IMainThread mMainThread, IGetTodoByIdInteractorCallback mCallback, ITodoRepository mRepo, int id) {
        super(mThreadExecutor, mMainThread, mRepo);
        this.id = id;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {

        final Todo todo = mRepo.getTodoById(id);

        // notify on the main thread that we have inserted this item
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onTodoRetrieved(todo);
            }
        });
    }
}
