package com.monkey.network.config.interceptor;

import java.util.List;

import okhttp3.Interceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-28 05:43
 */
public interface IInterceptorConfig {

    //添加拦截器
    void addInterceptor(Interceptor interceptor);

    //添加网络拦截器
    void addNetworkInterceptor(Interceptor interceptor);

    //获取拦截器集合
    List<Interceptor> getInterceptors();

    //获取网络拦截器集合

    List<Interceptor> getNetworkInterceptors();
}
