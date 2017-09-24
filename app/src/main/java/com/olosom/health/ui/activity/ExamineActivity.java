package com.olosom.health.ui.activity;

import android.os.Bundle;

import com.olosom.health.R;
import com.olosom.health.mvp.presenter.ExaminePresenter;
import com.olosom.health.mvp.view.ExamineView;
import com.olosom.health.ui.BaseActivity;

/**
 * 检测界面
 */

public class ExamineActivity extends BaseActivity<ExamineView, ExaminePresenter>
            implements ExamineView{


    @Override
    protected ExaminePresenter initPresenter() {
        return new ExaminePresenter();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_examine);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.addWaveData(1);
        mPresenter.addWaveData(2);
        mPresenter.addWaveData(3);
        mPresenter.addWaveData(4);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
