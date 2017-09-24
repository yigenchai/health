package com.olosom.health.mvp.model.listener;


import java.util.List;

/**
 * 获取数据列表监听
 */

public class OnGetDataListResultListener extends BaseResultListener {

    /**
     * 获取数据成功
     */
    public void onGetDataListSuccess(List dataList) {
        onSuccess();
    }

    /**
     * 更新页码信息
     */
    public void updatePageInfo(int currentPage, int pageCount) {

    }

    /**
     * 获取数据失败
     */
    public void onGetDataListFailure() {
        onFailure();
    }

    /**
     * 暂无数据
     */
    public void dataIsNotAvailable() {

    }

}
