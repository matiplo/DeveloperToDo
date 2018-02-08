package com.example.ppp.developertodo.implementation.networking;

import android.content.Context;

import com.example.ppp.developertodo.implementation.networking.converter.NetworkConverter;
import com.example.ppp.developertodo.implementation.networking.model.TodoDownloaded;
import com.example.ppp.developertodo.implementation.ui.activities.MainActivity;

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
    private static Context context = null;

    private static APIInterface apiInterface = null;
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static int counter = 0;

    public static APIService getInstance(Context context) {

        instance = new APIService(context);

        return instance;
    }


    private APIService(Context context) {


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(APIInterface.class);
        this.context = context;

    }

    public void downloadNextTodo() {

        Call<TodoDownloaded> call = apiInterface.getPost(++counter);
        call.enqueue(new Callback<TodoDownloaded>() {
            @Override
            public void onResponse(Call<TodoDownloaded> call, Response<TodoDownloaded> response) {
                TodoDownloaded todoDownloaded = response.body();

                ((MainActivity) context).onTodoDownloaded(NetworkConverter.convertToLogicModel(todoDownloaded));

            }

            @Override
            public void onFailure(Call<TodoDownloaded> call, Throwable t) {
                //   showMesssage(R.string.error_network);
            }
        });
    }
}


