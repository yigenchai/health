package com.olosom.health.mvp.model.listener;

/**
 * 单个返回结果的监听
 */

public class OnSingleResultListener extends BaseResultListener{

    public void onSuccess(Object result){
        onSuccess();
    }

    public void dataIsNotAvailable(){

    }

}
