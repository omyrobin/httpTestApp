package com.monkey.network.rxjava;


import io.reactivex.observers.DisposableObserver;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 13:25
 */
public abstract class Callback<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {
        onCompleted();
    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(Throwable throwable);
    public void onCompleted(){

    }


}
