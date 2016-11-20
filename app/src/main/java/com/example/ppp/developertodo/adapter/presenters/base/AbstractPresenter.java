package com.example.ppp.developertodo.adapter.presenters.base;


import com.example.ppp.developertodo.implementation.ui.interfaces.IView;
import com.example.ppp.developertodo.logic.executors.IExecutor;
import com.example.ppp.developertodo.logic.executors.IMainThread;
import com.example.ppp.developertodo.logic.repository.ITodoRepository;

/**
 * Created by ppp on 2016-11-10.
 */

public abstract class AbstractPresenter implements IPresenter {
    protected IExecutor mExecutor;
    protected IMainThread mMainThread;


    protected ITodoRepository mRepo;

    public AbstractPresenter(IExecutor executor, IMainThread mainThread,   ITodoRepository repo) {
        mExecutor = executor;
        mMainThread = mainThread;

        this.mRepo=repo;
    }


}
