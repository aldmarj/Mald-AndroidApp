package com.ems.ems.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by aldmar on 26/12/2017.
 */

public class WeatherAPIClient {
    private APIService apiService;

    public WeatherAPIClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        apiService = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/")
                .build()
                .create(APIService.class);

    }

    public APIService getApiService() {
        return apiService;
    }
}
