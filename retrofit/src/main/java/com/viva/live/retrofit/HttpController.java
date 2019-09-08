package com.viva.live.retrofit;

import androidx.annotation.NonNull;

import com.viva.live.retrofit.outer.IHttpConfigProvider;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
public class HttpController {
    private ConcurrentHashMap<Class, Retrofit> mClients = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Class, IHttpConfigProvider> mConfigs = new ConcurrentHashMap<>();

    private HttpController(){

    }

    private static class HttpControllerHolder{
        private static HttpController httpController = new HttpController();
    }

    public HttpController getInstance(){
        return HttpControllerHolder.httpController;
    }

    public synchronized <T> T getServiceInstance(@NonNull IHttpConfigProvider provider) {
        Class cls = provider.getHttpService();
        if (mClients.get(cls) == null || provider.refreshToken()) {
            mConfigs.put(cls, provider);
            mClients.put(cls, createRetrofit(provider));
        }
        return (T) mClients.get(cls);
    }

    private Retrofit createRetrofit(@NonNull IHttpConfigProvider provider){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(provider.getHttpParam().mTimeOut, TimeUnit.SECONDS);
        if (provider.getInterceptor() != null) {
            httpClientBuilder.addInterceptor(provider.getInterceptor());
        }

        Retrofit ret = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(provider.getHttpParam().getUrl())
                .build();
        return ret;
    }
}
