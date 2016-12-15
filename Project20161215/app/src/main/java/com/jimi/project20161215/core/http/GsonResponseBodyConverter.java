package com.jimi.project20161215.core.http;

import android.util.Log;

import com.google.gson.Gson;
import com.jimi.project20161215.core.entity.HttpResult;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        if (!httpResult.isSuccess()) {
            Log.e("TAG", "request error");
        }
        return gson.fromJson(response, type);
    }
}
