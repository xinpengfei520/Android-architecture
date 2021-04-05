package com.xpf.mvvm.net.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public final class ToStringConverter implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }

    public static Converter.Factory getConverter() {
        return new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                return new ToStringConverter();
            }
        };
    }
}