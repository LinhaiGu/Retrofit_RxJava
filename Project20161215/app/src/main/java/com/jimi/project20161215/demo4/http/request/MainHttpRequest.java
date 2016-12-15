package com.jimi.project20161215.demo4.http.request;


import com.jimi.project20161215.core.http.Http;
import com.jimi.project20161215.core.http.HttpResultFunc;
import com.jimi.project20161215.demo4.http.api.HomeCarouselService;
import com.jimi.project20161215.demo4.http.entity.NewsBean;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

/**
 * 首页相关的网络请求
 * Created by glh on 2016-12-14.
 */
public class MainHttpRequest {

    private HomeCarouselService mHomeCarouselService;
    private Http mHttpMethods;

    private MainHttpRequest() {
        mHttpMethods = Http.getInstance();
        mHomeCarouselService = mHttpMethods.getRetrofit().create(HomeCarouselService.class);
    }

    private static class SingletonHolder {
        private static final MainHttpRequest INSTANCE = new MainHttpRequest();
    }

    //获取单例
    public static MainHttpRequest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取广告
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void getBanner(Subscriber<ArrayList<NewsBean>> subscriber) {
        Observable observable = mHomeCarouselService.getNews()
                .map(new HttpResultFunc<ArrayList<NewsBean>>());
        mHttpMethods.getData(observable, subscriber);
    }

}
