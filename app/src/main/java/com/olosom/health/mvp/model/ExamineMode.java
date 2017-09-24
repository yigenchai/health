package com.olosom.health.mvp.model;

import android.util.Log;

import com.olosom.health.mvp.BaseModel;
import com.olosom.health.mvp.model.listener.OnSingleResultListener;
import com.olosom.health.utils.SDKHelper;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ExamineMode extends BaseModel {

    private SDKHelper mSdkHelper;


    public void addWaveData(int data, OnSingleResultListener onSingleResultListener) {
        mDisposable.add(
                addWaveData(data)
                        .doOnNext(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                if (integer == 2){
                                    Thread.sleep(100);
                                }
                                if (integer == 3){
                                    Thread.sleep(20);
                                }
                                Log.e("AAA", integer + ", name = " + Thread.currentThread().getId());
                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .subscribeWith(handleAddWaveData())
        );
    }

    private DisposableObserver<Integer> handleAddWaveData() {
        return new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


    private Observable<Integer> addWaveData(int data) {
        return Observable.just(data);
    }

}
