package com.example.ppp.developertodo.logic.executors;

/**
 * Created by ppp on 2016-11-10.
 */

import com.example.ppp.developertodo.logic.interactors.base.AbstractInteractor;

/**
 * This executor is responsible for running interactors on background threads.
 * <p/>

 */
public interface IExecutor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
