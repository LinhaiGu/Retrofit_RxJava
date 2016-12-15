package com.jimi.project20161215.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.jimi.project20161215.R;
import com.jimi.project20161215.demo1.api.APIService;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * <pre>
 *     此demo只演示在 gradle(Module)文件中引入：
 *     compile 'com.squareup.retrofit2:retrofit:2.1.0'
 *     案例。
 *
 * </pre>
 * Created by glh on 2016-12-15.
 */
public class DemoActivity1 extends Activity {

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).build();
        APIService apiService = retrofit.create(APIService.class);
        Call<ResponseBody> responseBodyCall = apiService.getBanner("10915", "585234059ab68");


        /*
        step2
        <pre>
            以下分两种方式请求，分别是同步和异步
        </pre>
         */
        synSendRequest(responseBodyCall);
//        asySendRequest(responseBodyCall);
    }


    /**
     * 同步
     *
     * @param responseBodyCall
     */
    private void synSendRequest(Call<ResponseBody> responseBodyCall) {
        MyHandler myHandler = new MyHandler(this);
        MyThread myThread = new MyThread(responseBodyCall, myHandler);
        myThread.start();
    }

    private void setData(String json) {
        tv_show.setText(json);
    }

    private static class MyHandler extends Handler {

        WeakReference<DemoActivity1> weakReference;

        public MyHandler(DemoActivity1 activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DemoActivity1 activity = weakReference.get();
            if (activity != null) {
                String json = (String) msg.obj;
                activity.setData(json);
            }
            super.handleMessage(msg);
        }
    }

    private static class MyThread extends Thread {
        private MyHandler myHandler;
        Call<ResponseBody> bodyResponse;

        public MyThread(Call<ResponseBody> responseBodyCall, MyHandler handler) {
            bodyResponse = responseBodyCall;
            myHandler = handler;
        }

        @Override
        public void run() {
            super.run();
            try {
                Response<ResponseBody> body = bodyResponse.execute();
                Message message = new Message();
                message.obj = body.body().string();
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
    private void asySendRequest(Call<ResponseBody> responseBodyCall) {

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    tv_show.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tv_show.setText("error");
            }
        });

    }

}
