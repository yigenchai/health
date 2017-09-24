package com.olosom.health.ui;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.olosom.health.R;
import com.olosom.health.mvp.BasePresenter;
import com.olosom.health.mvp.BaseView;
import com.olosom.health.rxpermissions.OnRequestPermissionListener;
import com.olosom.health.rxpermissions.RxPermissions;
import com.olosom.health.utils.CommonUtils;
import com.olosom.health.widget.bar.NormalTitleBar;

import io.reactivex.functions.Consumer;


/**
 * activity 基类，默认实现以下功能
 * 1 初始化 presenter
 * 2 在相应的生命周期对 view 的绑定和解绑
 * 3 权限管理
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends AppCompatActivity {

    public P mPresenter;

    private InputMethodManager mInputMethodManager;

    private RxPermissions mRxPermissions;

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initViews();
        registerTitleBarBackListener();
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // 初始化 presenter
        mPresenter = initPresenter();
        // 绑定 view
        if (mPresenter != null) {
            // activity 必须实现 view 接口，所以可以强转
            mPresenter.onCreate((V) this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑 view
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }


    /**
     * 初始化 presenter
     */
    protected abstract P initPresenter();

    protected abstract void setContentView();

    protected abstract void initViews();

    /**
     * 注册权限监听
     * 必须在 onCreate 处注册
     */
    public void registerPermissionManager() {
        mRxPermissions = new RxPermissions(this);
        mRxPermissions.setLogging(true);
    }

    /**
     * 获取本地广播管理器
     */
    public LocalBroadcastManager getLocalBroadcastManager(){
        if (mLocalBroadcastManager == null){
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        }
        return mLocalBroadcastManager;
    }

    /**
     * 请求相关权限
     *
     * @param permissions 权限列表
     */
    public void requestPermissions(final OnRequestPermissionListener onRequestPermissionListener,
                                   final String deniedNotify,
                                   String... permissions) {
        if (mRxPermissions != null) {// 必须先注册 registerPermissions();
            mRxPermissions.request(permissions)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean result) throws Exception {
                            if (result){
                                onRequestPermissionListener.onGrantedPermission();
                            } else {
                                onRequestPermissionListener.onDeniedPermission(BaseActivity.this, deniedNotify);
                            }
                        }
                    });
        }
    }

    /**
     * 销毁 activity
     */
    public void back() {
        hideKeyboard();
        finish();
    }

    /**
     * 注册 TitleBar 返回监听
     */
    public void registerTitleBarBackListener() {
        NormalTitleBar normalTitleBar = (NormalTitleBar) findViewById(R.id.titleBar);
        if (normalTitleBar != null) {
            normalTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                }
            });
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode
                != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            }
            if (mInputMethodManager.isActive() && getCurrentFocus() != null) {
                try {
                    mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * 显示网络不可用
     */
    public void showNetworkError() {
        CommonUtils.showToast(this, getString(R.string.network_error));
    }

}
