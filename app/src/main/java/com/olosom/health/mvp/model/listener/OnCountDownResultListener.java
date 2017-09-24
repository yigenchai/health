package com.olosom.health.mvp.model.listener;

/**
 * 倒计时结果监听
 */

public interface OnCountDownResultListener {

    void countDownResult(int result);

    void onCountDownComplete();

}
