package com.ems.ems.API;

import com.ems.ems.API.BusinessPojo.Business;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.GooglePojo.GeoLocation;
import com.ems.ems.API.WeatherPojo.WeatherCard;
import com.ems.ems.API.WorkLogPojo.WorkLog;

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
import retrofit2.http.Query;
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
    @POST("/business/{businessTag}/client")
    Call<String> postClient(@Path("businessTag") String businessTag, @QueryMap(encoded = true) Map<String, String> params,
                          @Body String newClient);

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("maps/api/geocode/json")
    Call<GeoLocation> getLatLong(@Query("address") String postcode);

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("data/2.5/weather?lat=50.376289&lon=-4.143841&units=metric&APPID=ea11946759095eabe7638933c7a344b6")
    Call<WeatherCard> getWeather();


}
