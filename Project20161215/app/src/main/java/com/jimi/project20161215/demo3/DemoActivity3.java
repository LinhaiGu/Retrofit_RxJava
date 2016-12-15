package com.jimi.project20161215.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jimi.project20161215.R;
import com.jimi.project20161215.demo2.entity.HttpResult;
import com.jimi.project20161215.demo3.api.APIService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <pre>
 *     此demo只演示在 gradle(Module)文件中引入：
 *     compile 'com.squareup.retrofit2:retrofit:2.1.0'
 *     compile 'com.squareup.retrofit2:converter-gson:2.1.0'
 *     compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
 *     compile 'io.reactivex:rxjava:1.1.0'
 *     compile 'io.reactivex:rxandroid:1.1.0'
 *     案例。
 *
 * </pre>
 * Created by glh on 2016-12-15.
 */
public class DemoActivity3 extends Activity {
    private static final String API_URL = "http://n1.jimi.la/apps_V9.4/";


    @Bind(R.id.tv_show)
    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo1);
        ButterKnife.bind(this);
        sendRequest();
    }


    private void sendRequest() {

        //step1
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);
        Observable<HttpResult> httpResultObservable = apiService.getBanner("10915", "58524bb42c9ca");

        httpResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HttpResult>() {
            @Override
            public void onCompleted() {
                tv_show.append("请求结束");
            }

            @Override
            public void onError(Throwable e) {
                tv_show.append("请求错误");
            }

            @Override
            public void onNext(HttpResult httpResult) {
                tv_show.append(httpResult.data.pc.number);
            }

            @Override
            public void onStart() {
                tv_show.append("请求开始");
            }
        });

    }


}
