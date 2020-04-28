package com.monkey.network.config.okhttp;

import com.monkey.network.config.interceptor.IInterceptorConfig;
import com.monkey.network.config.interceptor.InterceptorArray;
import com.monkey.network.config.ssl.ISSLConfig;

import java.util.List;

import okhttp3.Interceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-28 06:07
 */
public abstract class OkConfig implements IOkConfig, IInterceptorConfig, ISSLConfig {

    @Override
    public void addInterceptor(Interceptor interceptor) {
        if(interceptor != null){
            InterceptorArray.interceptors.add(interceptor);
        }else {
            throw new IllegalArgumentException("interceptor must be not null");
        }
    }

    @Override
    public void addNetworkInterceptor(Interceptor interceptor) {
        if(interceptor != null){
            InterceptorArray.networkInterceptors.add(interceptor);
        }else {
            throw new IllegalArgumentException("interceptor must be not null");
        }
    }

    @Override
    public List<Interceptor> getInterceptors() {
        return InterceptorArray.interceptors;
    }

    @Override
    public List<Interceptor> getNetworkInterceptors() {
        return InterceptorArray.networkInterceptors;
    }
}
