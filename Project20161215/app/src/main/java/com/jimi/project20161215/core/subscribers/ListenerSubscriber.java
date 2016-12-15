package com.jimi.project20161215.core.subscribers;

import android.content.Context;

import rx.Subscriber;

public class ListenerSubscriber<T> extends Subscriber<T> {

    private OnFunctionListener mSubscriberOnNextListener;
    private Context context;


    public ListenerSubscriber(OnFunctionListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }


    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.fail("error");
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.success(t);
        }
    }

}