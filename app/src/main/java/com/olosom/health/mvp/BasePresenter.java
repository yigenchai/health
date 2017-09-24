package com.olosom.health.mvp;

import com.olosom.health.app.App;
import com.olosom.health.utils.CommonUtils;

/**
 * 界面控制器基类
 */

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V mView;

    protected M mModel;

    public BasePresenter() {
        mModel = initModule();
        if (mModel != null){
            mModel.onCreate();
        }
    }

    /**
     * 设置 Model 模块
     */
    protected abstract M initModule();

    /**
     * 绑定View
     */
    public void onCreate(V view) {
        this.mView = view;
    }

    /**
     * 将 view 的引用清空
     * 当 presenter 有耗时任务未结束，而 activity 要销毁时，
     * 如果 presenter 持有 view的引用，而 view 持有 activity 的引用，将导致 activity 不能被回收
     * 所以在监测到 activity 的 onDestroy 方法时，
     * 将 presenter 中的 view 引用清空，让 activity 得以回收
     * 将 Rx 任务清空
     */
    public void onDestroy() {
        this.mView = null;
        if (mModel != null) {
            mModel.onDestroy();
        }
    }

    /**
     * activity 生命周期
     */
    public void onResume() {
        if (mModel != null) {
            mModel.onResume();
        }
    }

    /**
     * activity 生命周期
     */
    public void onStop() {
        if (mModel != null) {
            mModel.onStop();
        }
    }

    /**
     * 检测网络是否可用
     */
    protected boolean checkNetwork(){
        return CommonUtils.isNetWorkConnected(App.getInstance().getApplicationContext());
    }

    /**
     * 是否 wifi 网络
     */
    protected boolean checkIsWifiNetwork(){
        return CommonUtils.isWifi(App.getInstance().getApplicationContext());
    }



}
