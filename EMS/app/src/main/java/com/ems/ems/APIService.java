package com.ems.ems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by aldmar on 22/11/2017.
 */

public interface APIService {


    @GET("/business")
    Call<List<Business>>getBusiness();

}
