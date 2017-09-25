package com.olosom.health.app;

import android.app.Application;

/**
 * 程序入口
 */

public class App extends Application {

    private static App sInstance;

    /**
     * 全局获取单例
     */
    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


    //测试一下哦123456789321654

}
