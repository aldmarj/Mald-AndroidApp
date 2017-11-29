package com.ems.ems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aldmar on 22/11/2017.
 */

public interface APIService {


    @GET("/business")
    Call<List<Business>> getBusiness();


    @POST("/business/bae/login")
    @FormUrlEncoded
    Call<String> checkCredentials(@Field("u") String username,
                                  @Field("p") String password);

}
