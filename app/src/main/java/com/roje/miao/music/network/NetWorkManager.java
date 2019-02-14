package com.roje.miao.music.network;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum NetWorkManager {

    INSTANCE;

    private Retrofit retrofit;

    private volatile Request request = null;

    NetWorkManager() {
        //初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder().build();
        //初始化retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Request.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                if (request == null) {
                    request = retrofit.create(Request.class);
                }
            }
        }
        return request;
    }
}
