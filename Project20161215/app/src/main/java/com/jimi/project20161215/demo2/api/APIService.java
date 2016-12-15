package com.jimi.project20161215.demo2.api;

import com.jimi.project20161215.demo2.entity.HttpResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 一系列用于访问接口的地址
 * Created by glh on 2016-12-15.
 */
public interface APIService {

    @GET("getBrandBanner.php")
    Call<HttpResult> getBanner(@Query("uid") String _uid, @Query("token") String _token);

}
