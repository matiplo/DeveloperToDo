package com.example.ppp.developertodo.implementation.networking;

import com.example.ppp.developertodo.implementation.networking.model.TodoDownloaded;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ppp on 2018-02-07.
 */

public interface APIInterface {

    @GET("todos/{id}")
    Call<TodoDownloaded> getPost(@Path("id") int id);
}
