package com.jimi.project20161215.demo1.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 一系列用于访问接口的地址
 * Created by glh on 2016-12-15.
 */
public interface APIService {

    @GET("getBrandBanner.php")
    Call<ResponseBody> getBanner(@Query("uid") String _uid, @Query("token") String _token);

    @GET("getHomePager.php")
    Call<ResponseBody> getHomePager();

    @FormUrlEncoded
    @POST("editUserInfo.php")
    Call<ResponseBody> postIP(@Field("name") String name, @Field("age") int age);
}
