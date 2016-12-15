package com.jimi.project20161215.core.http;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Charles on 2016/3/17.
 */
public class ResponseConvertFactory extends Converter.Factory {

    private final Gson gson;

    public static ResponseConvertFactory create() {
        return create(new Gson());
    }

    public static ResponseConvertFactory create(Gson gson) {
        return new ResponseConvertFactory(gson);
    }


    private ResponseConvertFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(gson, type);
    }

}
