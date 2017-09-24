package com.olosom.health.mvp.view.common;

/**
 * 更新页码
 */

public interface UpdatePageInfoView {

    /**
     * 更新当前页码信息
     *
     * @param currentPage 当前页
     * @param pageCount   总页码
     */
    void updatePageInfo(int currentPage, int pageCount);

}
