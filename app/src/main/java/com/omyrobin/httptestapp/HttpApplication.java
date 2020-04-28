package com.omyrobin.httptestapp;

import android.app.Application;

import com.monkey.network.NetWorkManager;
import com.monkey.network.config.okhttp.OkConfig;
import com.monkey.network.config.retrofit.IRerofitConfig;
import com.omyrobin.httptestapp.config.MyOkConfig;
import com.omyrobin.httptestapp.config.MyRerofitConfig;
import com.omyrobin.httptestapp.interceptor.UserAgentInterceptor;
import com.omyrobin.httptestapp.interceptor.UserTokenInterceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-26 10:38
 */
public class HttpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        initHttp();

        initCustomHttp();
    }

    private void initHttp(){
        //Retrofit BaseUrl Config
        IRerofitConfig rerofitConfig = new MyRerofitConfig();
        //默认初始化
        NetWorkManager.getInstance().init(this, rerofitConfig);
    }

    private void initCustomHttp(){
        //OkhtpClient Config
        OkConfig okConfig = new MyOkConfig();

        final UserAgentInterceptor userAgentInterceptor = new UserAgentInterceptor();
        final UserTokenInterceptor userTokenInterceptor = new UserTokenInterceptor();
        okConfig.addInterceptor(userAgentInterceptor);
        okConfig.addInterceptor(userTokenInterceptor);

        IRerofitConfig rerofitConfig = new MyRerofitConfig();

        //自定义初始化
        NetWorkManager.getInstance().init(this, okConfig, rerofitConfig);

    }
}
