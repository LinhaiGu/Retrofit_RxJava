package com.jimi.project20161215.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.jimi.project20161215.R;
import com.jimi.project20161215.demo2.api.APIService;
import com.jimi.project20161215.demo2.entity.HotBean;
import com.jimi.project20161215.demo2.entity.HttpResult;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     此demo只演示在 gradle(Module)文件中引入：
 *     compile 'com.squareup.retrofit2:retrofit:2.1.0'
 *     compile 'com.squareup.retrofit2:converter-gson:2.1.0'
 *     案例。
 *
 * </pre>
 * Created by glh on 2016-12-15.
 */
public class DemoActivity2 extends Activity {
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);
        Call<HttpResult> responseBodyCall = apiService.getBanner("10915", "58524bb42c9ca");


        /*
        step2
        <pre>
            以下分两种方式请求，分别是同步和异步
        </pre>
         */
//        synSendRequest(responseBodyCall);
        asySendRequest(responseBodyCall);
    }


    /**
     * 同步
     *
     * @param responseBodyCall
     */
    private void synSendRequest(Call<HttpResult> responseBodyCall) {
        MyHandler myHandler = new MyHandler(this);
        MyThread myThread = new MyThread(responseBodyCall, myHandler);
        myThread.start();
    }

    private void setData(String data) {
        tv_show.setText(data);
    }

    private static class MyHandler extends Handler {

        WeakReference<DemoActivity2> weakReference;

        public MyHandler(DemoActivity2 activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DemoActivity2 activity = weakReference.get();
            if (activity != null) {
                HttpResult hotBean = (HttpResult) msg.obj;
                if (hotBean != null) {
                    activity.setData(hotBean.data.pc.number);
                }
            }
            super.handleMessage(msg);
        }
    }

    private static class MyThread extends Thread {
        private MyHandler myHandler;
        Call<HttpResult> bodyResponse;

        public MyThread(Call<HttpResult> responseBodyCall, MyHandler handler) {
            bodyResponse = responseBodyCall;
            myHandler = handler;
        }

        @Override
        public void run() {
            super.run();
            try {
                Response<HttpResult> body = bodyResponse.execute();
                Message message = new Message();
                message.obj = body.body();
                myHandler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 异步
     *
     * @param responseBodyCall
     */
    private void asySendRequest(Call<HttpResult> responseBodyCall) {

        responseBodyCall.enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
                HotBean hotBean = response.body().data;
                tv_show.setText(hotBean.pc.number);
            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {
                tv_show.setText("error");
            }
        });

    }

}
