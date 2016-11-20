package com.example.ppp.developertodo.logic.interactors.implementations;

import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.base.AbstractInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractorCallback;
import com.example.ppp.developertodo.logic.interactors.interfaces.ISaveTodoInteractorCallback;
import com.example.ppp.developertodo.logic.model.Todo;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

/**
 * Created by ppp on 2016-11-12.
 */

public class SaveTodoInteractor extends AbstractInteractor implements IInteractor {

    private Todo todo;

    private ISaveTodoInteractorCallback mCallback;

    public SaveTodoInteractor(IExecutor mThreadExecutor, IMainThread mMainThread, ISaveTodoInteractorCallback mCallback, ITodoRepository mRepo, Todo todo) {
        super(mThreadExecutor, mMainThread, mRepo);
        this.todo = todo;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {

        mRepo.insert(this.todo);

        // notify on the main thread that we have inserted this item
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onTodoSaved();
            }
        });
    }
}
    ;


