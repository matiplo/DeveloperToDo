package com.example.ppp.developertodo.implementation.networking;

import android.content.Context;

import com.example.ppp.developertodo.implementation.networking.converter.NetworkConverter;
import com.example.ppp.developertodo.implementation.networking.model.TodoDownloaded;
import com.example.ppp.developertodo.logic.model.Todo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ppp on 2018-02-07.
 */

public class APIService {

    private static APIService instance = null;
    private static onNetworkInteractionListener mListener = null;

    private APIInterface apiInterface = null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private int counter = 0;

    public static APIService getInstance(Context context) {

        if (instance == null) {
            instance = new APIService();
        }

        if (context instanceof onNetworkInteractionListener) {
            mListener = (onNetworkInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onNetworkInteractionListener");
        }

        return instance;
    }


    private APIService() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(APIInterface.class);


    }

    public interface onNetworkInteractionListener {
        void onTodoDownloaded(Todo todo);
    }

    public void downloadNextTodo() {

        Call<TodoDownloaded> call = apiInterface.getPost(++counter);
        call.enqueue(new Callback<TodoDownloaded>() {
            @Override
            public void onResponse(Call<TodoDownloaded> call, Response<TodoDownloaded> response) {
                TodoDownloaded todoDownloaded = response.body();

                mListener.onTodoDownloaded(NetworkConverter.convertToLogicModel(todoDownloaded));

            }

            @Override
            public void onFailure(Call<TodoDownloaded> call, Throwable t) {
                //   showMesssage(R.string.error_network);
            }
        });
    }
}


