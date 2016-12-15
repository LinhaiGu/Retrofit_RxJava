package com.jimi.project20161215.demo4.http.api;

import com.jimi.project20161215.core.entity.HttpResult;
import com.jimi.project20161215.demo4.http.entity.NewsBean;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 首页相关请求
 * Created by glh on 2016-12-15.
 */
public interface HomeCarouselService {
    @GET("getHomeCarousel.php")
    Observable<HttpResult<ArrayList<NewsBean>>> getNews();
}
