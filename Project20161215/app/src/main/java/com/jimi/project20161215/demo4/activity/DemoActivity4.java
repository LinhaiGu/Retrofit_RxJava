package com.jimi.project20161215.demo4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jimi.project20161215.R;
import com.jimi.project20161215.core.subscribers.ListenerSubscriber;
import com.jimi.project20161215.core.subscribers.OnFunctionListener;
import com.jimi.project20161215.demo4.http.entity.NewsBean;
import com.jimi.project20161215.demo4.http.request.MainHttpRequest;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by glh on 2016-12-15.
 */
public class DemoActivity4 extends Activity {

    private OnFunctionListener getNewsListener = new OnFunctionListener<ArrayList<NewsBean>>() {
        @Override
        public void success(ArrayList<NewsBean> o) {
            tv_show.setText(o.get(0).lname);
        }

        @Override
        public void fail(String message) {

        }

    };

    @Bind(R.id.tv_show)
    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo1);
        ButterKnife.bind(this);
        getNews();
    }


    private void getNews() {
        MainHttpRequest.getInstance().getBanner(new ListenerSubscriber<ArrayList<NewsBean>>(getNewsListener, DemoActivity4.this));
    }


}
