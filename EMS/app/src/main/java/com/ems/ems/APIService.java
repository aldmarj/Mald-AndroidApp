package com.ems.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.common.api.ApiException;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
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


    @POST("/business/bae/login")
    @FormUrlEncoded
    Call<String> checkCredentials(@Field("u") String username,
                                  @Field("p") String password);

    @GET("/business/bae/client")
    Call<List<Client>> getClient (@QueryMap(encoded=true) Map<String, String> params);

    //throws ApiException;
    //value = , encoded=true
    //Call<List<Client>> getClient(@Path("token") String token);
    //(@Query("token") String token,
    //@QueryMap Map<String, String>)

}
