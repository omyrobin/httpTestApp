package com.monkey.network.config.okhttp;

import com.monkey.network.config.interceptor.IInterceptorConfig;
import com.monkey.network.config.ssl.ISSLConfig;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-28 06:07
 */
public abstract class OkConfig implements IOkConfig, IInterceptorConfig, ISSLConfig {

    public List<Interceptor> interceptors = new ArrayList<>();

    public List<Interceptor> networkInterceptors = new ArrayList<>();

    @Override
    public void addInterceptor(Interceptor interceptor) {
        if(interceptor != null){
            interceptors.add(interceptor);
        }else {
            throw new IllegalArgumentException("interceptor must be not null");
        }
    }

    @Override
    public void addNetworkInterceptor(Interceptor interceptor) {
        if(interceptor != null){
            networkInterceptors.add(interceptor);
        }else {
            throw new IllegalArgumentException("interceptor must be not null");
        }
    }

    @Override
    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    @Override
    public List<Interceptor> getNetworkInterceptors() {
        return networkInterceptors;
    }
}
