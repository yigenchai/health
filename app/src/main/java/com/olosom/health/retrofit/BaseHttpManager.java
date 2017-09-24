package com.olosom.health.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 配置 OKHttp
 * 1. 超时设定 已配置
 * 2. 网络缓存 cache 未配置
 * 3. 持久 cookie 未配置
 * 4. 拦截器 未配置
 */

public class BaseHttpManager {

    private final OkHttpClient mClient;

    public OkHttpClient getHttpClient() {
        return mClient;
    }

    public BaseHttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        mClient = builder.build();
    }

}
