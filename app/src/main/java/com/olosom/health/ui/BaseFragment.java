package com.olosom.health.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.olosom.health.R;
import com.olosom.health.mvp.BasePresenter;
import com.olosom.health.mvp.BaseView;
import com.olosom.health.rxpermissions.OnRequestPermissionListener;
import com.olosom.health.rxpermissions.RxPermissions;
import com.olosom.health.utils.CommonUtils;

import io.reactivex.functions.Consumer;

/**
 * fragment 基类，默认实现以下功能
 * 1 初始化 presenter
 * 2 在相应的生命周期对 view 的绑定和解绑
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment {


    public P mPresenter;

    private InputMethodManager mInputMethodManager;

    private RxPermissions mRxPermissions;

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // 初始化 presenter
        mPresenter = initPresenter();

        // 绑定 view
        if (mPresenter != null) {
            // fragment 必须实现 view 接口，所以可以强转
            mPresenter.onCreate((V) this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 解绑 view
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    /**
     * 初始化 presenter
     */
    protected abstract P initPresenter();

    /**
     * 注册权限监听
     * 必须在 onCreate 处注册
     */
    public void registerPermissionManager() {
        mRxPermissions = new RxPermissions(getActivity());
        mRxPermissions.setLogging(true);
    }

    /**
     * 请求相关条件
     *
     * @param permissions
     */
    public void requestPermissions(final OnRequestPermissionListener onRequestPermissionListener,
                                   final String deniedNotify,
                                   String... permissions) {
        if (mRxPermissions != null) {// 必须先注册 registerPermissions();
            mRxPermissions.request(permissions)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean result) throws Exception {
                            if (result) {
                                onRequestPermissionListener.onGrantedPermission();
                            } else {
                                onRequestPermissionListener.onDeniedPermission(getContext(), deniedNotify);
                            }
                        }
                    });
        }
    }

    public LocalBroadcastManager getLocalBroadcastManager(){
        if (mLocalBroadcastManager == null){
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        }
        return mLocalBroadcastManager;
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        FragmentActivity activity = getActivity();
        if (activity.getWindow().getAttributes().softInputMode
                != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            }
            if (mInputMethodManager.isActive() && activity.getCurrentFocus() != null) {
                try {
                    mInputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
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
        CommonUtils.showToast(getActivity(), getString(R.string.network_error));
    }

}

