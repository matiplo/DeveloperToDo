package com.example.ppp.developertodo.logic.interactors.implementations;

import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.interactors.base.AbstractInteractor;
import com.example.ppp.developertodo.logic.interactors.base.IInteractor;
import com.example.ppp.developertodo.logic.interactors.interfaces.IDeleteTodosByIdInteractorCallback;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

import java.util.List;

/**
 * Created by ppp on 2016-11-15.
 */



    public class DeleteTodosByIdInteractor extends AbstractInteractor implements IInteractor {

        private IDeleteTodosByIdInteractorCallback mCallback;
        private List<Integer> mTodos;

        public DeleteTodosByIdInteractor(IExecutor mThreadExecutor, IMainThread mMainThread, IDeleteTodosByIdInteractorCallback mCallback, ITodoRepository mRepo, List<Integer> todos) {
            super(mThreadExecutor, mMainThread, mRepo);
            this.mTodos =todos;
            this.mCallback = mCallback;
        }

        @Override
        public void run() {

            mRepo.deleteTodosById(mTodos);

            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onTodosDeleted();
                }
            });
        }
    }

