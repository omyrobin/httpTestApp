package com.omyrobin.httptestapp.rxjava;

import com.monkey.network.exception.ApiException;
import com.monkey.network.rxjava.tranformer.SchedulersTranformer;
import com.omyrobin.httptestapp.bean.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 11:07
 */
public class BaseTranformer<T> implements ObservableTransformer<BaseResponse<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream.onErrorResumeNext(new ErrorResumeFunction<T>())
                .flatMap(new ResponseFunction<T>())
                .compose(new SchedulersTranformer<T>());
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T> Response 对应的javaBean
     */
    private class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends BaseResponse<T>>> {

        @Override
        public ObservableSource<? extends BaseResponse<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(ApiException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的异常
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseResponse<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseResponse<T> tResponse) throws Exception {
            int code = tResponse.getCode();
            String message = tResponse.getMessage();
            if (code == 200) {
                return handlerResponse(tResponse.getData());
            } else {
                return Observable.error(new ApiException.ResponseThrowable(code, message));
            }
        }
    }

    private static <T> ObservableSource<T> handlerResponse(final T t) {
        return new Observable<T>() {
            @Override
            protected void subscribeActual(Observer<? super T> observer) {
                try {
                    observer.onNext(t);
                    observer.onComplete();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        };
    }
}
