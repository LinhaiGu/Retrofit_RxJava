package com.jimi.project20161215;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jimi.project20161215.demo1.DemoActivity1;
import com.jimi.project20161215.demo2.DemoActivity2;
import com.jimi.project20161215.demo3.DemoActivity3;
import com.jimi.project20161215.demo4.activity.DemoActivity4;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_demo1)
    Button Demo_1;

    @Bind(R.id.btn_demo2)
    Button Demo_2;

    @Bind(R.id.btn_demo3)
    Button Demo_3;

    @Bind(R.id.btn_demo4)
    Button Demo_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_demo1)
    public void Demo1() {
        startActivity(new Intent(this, DemoActivity1.class));
    }

    @OnClick(R.id.btn_demo2)
    public void Demo2() {
        startActivity(new Intent(this, DemoActivity2.class));
    }

    @OnClick(R.id.btn_demo3)
    public void Demo3() {
        startActivity(new Intent(this, DemoActivity3.class));
    }

    @OnClick(R.id.btn_demo4)
    public void Demo4() {
        startActivity(new Intent(this, DemoActivity4.class));
    }
}
