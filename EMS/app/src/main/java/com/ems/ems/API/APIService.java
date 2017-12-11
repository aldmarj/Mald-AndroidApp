package com.ems.ems.API;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by aldmar on 22/11/2017.
 */

public interface APIService {

    @GET("/business")
    Call<List<Business>> getBusiness();


    @POST("/business/cibusinesstag/login")
    @FormUrlEncoded
    Call<String> checkCredentials(@Field("u") String username,
                                  @Field("p") String password);

    @GET("/business/cibusinesstag/client")
    Call<List<Client>> getClient(@QueryMap(encoded = true) Map<String, String> params);

    @GET("/business/cibusinesstag/worklog/range/0/1")
    Call<List<WorkLog>> getWorkLog(@QueryMap(encoded = true) Map<String, String> params);


}
