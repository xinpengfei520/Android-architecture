package com.xpf.mvvm.net.factory;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by x-sir on 4/5/21 :)
 * Function:封装全局转换器的工厂类
 */
public class AnnotatedConverterFactory extends Converter.Factory {

    /**
     * 解析 Json 的转换器
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Json {
    }

    /**
     * 解析 xml 的转换器
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Xml {
    }

    /**
     * 解析字符串类型的转换器
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Str {
    }

    final Map<Class<?>, Converter.Factory> mFactoryMap;

    public AnnotatedConverterFactory(Map<Class<?>, Converter.Factory> factoryMap) {
        mFactoryMap = factoryMap;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            Converter.Factory factory = mFactoryMap.get(annotation.annotationType());
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit);
            }
        }

        // try to default to json in case no annotation on current method was found
        Converter.Factory jsonFactory = mFactoryMap.get(Json.class);
        if (jsonFactory != null) {
            return jsonFactory.responseBodyConverter(type, annotations, retrofit);
        }

        return null;
    }

    public static class Builder {
        Map<Class<?>, Converter.Factory> mFactoryMap = new LinkedHashMap<>();

        public Builder add(Class<? extends Annotation> factoryType, Converter.Factory factory) {
            if (factoryType == null) {
                throw new NullPointerException("factoryType is null");
            }
            if (factory == null) {
                throw new NullPointerException("factory is null");
            }
            mFactoryMap.put(factoryType, factory);
            return this;
        }

        public AnnotatedConverterFactory build() {
            return new AnnotatedConverterFactory(mFactoryMap);
        }

    }
}
