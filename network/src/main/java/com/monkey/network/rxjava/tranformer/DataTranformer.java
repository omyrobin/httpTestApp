package com.monkey.network.rxjava.tranformer;

import com.monkey.network.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @Description: 封装请求过程 全局统一的异常处理
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 10:41
 */
public class DataTranformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.onErrorResumeNext(new ErrorResumeFunction<T>()).compose(new SchedulersTranformer<T>());
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     * @param <T> Response 对应的javaBean
     */
    private class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends T>> {

        @Override
        public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
            return Observable.error(ApiException.handleException(throwable));
        }
    }
}
