package com.example.ppp.developertodo.implementation.networking.converter;

import com.example.ppp.developertodo.implementation.networking.model.TodoDownloaded;
import com.example.ppp.developertodo.logic.model.Todo;

/**
 * Created by ppp on 2018-02-08.
 */

public class NetworkConverter {
    public static Todo convertToLogicModel(TodoDownloaded todoDownloaded) {
        return new Todo(todoDownloaded.getmText(), todoDownloaded.getmId());
    }


}
