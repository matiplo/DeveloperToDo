package com.example.ppp.developertodo.logic.interactors.base;

/**
 * Created by ppp on 2016-11-10.
 */

public interface IInteractor {

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
