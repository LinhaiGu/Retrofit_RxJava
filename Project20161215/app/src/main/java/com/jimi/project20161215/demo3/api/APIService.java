package com.jimi.project20161215.demo3.api;

import com.jimi.project20161215.demo2.entity.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 一系列用于访问接口的地址
 * Created by glh on 2016-12-15.
 */
public interface APIService {

    @GET("getBrandBanner.php")
    Observable<HttpResult> getBanner(@Query("uid") String _uid, @Query("token") String _token);

}
