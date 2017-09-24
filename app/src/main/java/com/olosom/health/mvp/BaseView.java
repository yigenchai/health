package com.olosom.health.mvp;

/**
 * 界面所需的行为基类
 */

public interface BaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示网络异常
     */
    void showNetworkError();

}
