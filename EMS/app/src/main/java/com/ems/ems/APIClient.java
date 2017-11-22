package com.ems.ems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aldmar on 22/11/2017.
 */

public class APIClient {
    private APIService apiService;

    public APIClient(){
    Gson gson = new GsonBuilder().create();
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        apiService = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://lousy.me.uk")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }
}
