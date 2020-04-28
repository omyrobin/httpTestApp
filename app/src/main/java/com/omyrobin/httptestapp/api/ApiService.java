package com.omyrobin.httptestapp.api;

import com.omyrobin.httptestapp.bean.BaseResponse;
import com.omyrobin.httptestapp.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-26 18:01
 */
public interface ApiService {
    @GET("/api/xx")
    Observable<BaseResponse<TestBean>> getList();
}
