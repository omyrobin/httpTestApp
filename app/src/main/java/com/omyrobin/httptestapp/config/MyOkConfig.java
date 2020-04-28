package com.omyrobin.httptestapp.config;

import com.monkey.network.config.okhttp.OkConfig;
import com.monkey.network.config.ssl.SSLState;

import java.util.List;

import okhttp3.Interceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 12:28
 */
public class MyOkConfig extends OkConfig {

    @Override
    public int getConnectTimeOut() {
        return 0;
    }

    @Override
    public int getWriteTimeOut() {
        return 0;
    }

    @Override
    public int getReadTimeOut() {
        return 0;
    }

    @Override
    public String getCacheDirPath() {
        return null;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    @Override
    public void addNetworkInterceptor(Interceptor interceptor) {
        super.addNetworkInterceptor(interceptor);
    }

    @Override
    public List<Interceptor> getInterceptors() {
        return super.getInterceptors();
    }

    @Override
    public List<Interceptor> getNetworkInterceptors() {
        return super.getNetworkInterceptors();
    }

    @Override
    public SSLState getSSLState() {
        return SSLState.DOUBLE;
    }

    @Override
    public String getClientCer() {
        return "lh_client.bks";
    }

    @Override
    public String getClientPassWord() {
        return "000000";
    }

    @Override
    public String getServerCer() {
        return "lh_server.cer";
    }


}
