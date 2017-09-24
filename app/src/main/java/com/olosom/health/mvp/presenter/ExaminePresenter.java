package com.olosom.health.mvp.presenter;

import com.olosom.health.mvp.BasePresenter;
import com.olosom.health.mvp.model.ExamineMode;
import com.olosom.health.mvp.view.ExamineView;

public class ExaminePresenter extends BasePresenter<ExamineView, ExamineMode> {

    @Override
    protected ExamineMode initModule() {
        return new ExamineMode();
    }

    public void addWaveData(int data){
        mModel.addWaveData(data, null);
    }

}
