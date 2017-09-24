package com.olosom.health.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 业务基类
 */

public class BaseModel {

    protected CompositeDisposable mDisposable;

    public BaseModel() {
        onCreate();
    }

    /**
     * 设置 Rx 模式
     */
    public void onCreate(){
        // 初始化 Rx
        mDisposable = new CompositeDisposable();
    }

    /**
     * 清除 Rx 任务
     */
    public void onDestroy(){
        mDisposable.clear();
        mDisposable = null;
    }

    /**
     * activity 生命周期
     */
    public void onResume(){

    }

    /**
     * activity 生命周期
     */
    public void onStop(){

    }

}
