package com.jimi.project20161215.core.subscribers;

public interface OnFunctionListener<T> {
    void success(T t);
    void fail(String message);
}
