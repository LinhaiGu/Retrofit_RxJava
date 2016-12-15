package com.jimi.project20161215.core.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Http {

    public static final String BASE_URL = "http://n1.glh.la/apps_T1/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;


    //构造方法私有
    private Http() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

    }

    private static class SingletonHolder {
        private static final Http INSTANCE = new Http();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static Http getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <T> void getData(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

}
