package com.ems.ems.API;

import com.ems.ems.API.GooglePojo.GeoLocation;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by aldmar on 22/11/2017.
 */

public interface APIService {

    @GET("/business")
    Call<List<Business>> getBusiness();

    @POST("/business/{businessTag}/login")
    @FormUrlEncoded
    Call<String> checkCredentials(@Path("businessTag") String businessTag, @Field("u") String username,
                                  @Field("p") String password);

    @GET("/business/{businessTag}/client")
    Call<List<Client>> getClient(@Path("businessTag") String businessTag, @QueryMap(encoded = true) Map<String, String> params);

    @GET("/business/{businessTag}/client/{clientId}")
    Call<List<Client>> getClientById(@Path("businessTag") String businessTag,
                                     @Path("clientId") String clientId,
                                     @QueryMap(encoded = true) Map<String, String> params);

    @GET("/business/{businessTag}/worklog/range/0/1")
    Call<List<WorkLog>> getWorkLog(@Path("businessTag") String businessTag, @QueryMap(encoded = true) Map<String, String> params);

    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("/business/{businessTag}/worklog")
    Call<String> postWork(@Path("businessTag") String businessTag, @QueryMap(encoded = true) Map<String, String> params,
                          @Body String worklog);

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyBok17yEyr1SeNcldrxJrEbkHyDpImRhgg")
    Call<GeoLocation> getLatLong();


}
