package com.omyrobin.httptestapp.config;

import com.monkey.network.config.retrofit.IRerofitConfig;
import com.omyrobin.httptestapp.BuildConfig;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 19:11
 */
public class MyRerofitConfig implements IRerofitConfig {
    @Override
    public String getBaseUrl() {
        if(BuildConfig.DEBUG){
            return "http://www.baidu.com";
        }else{
            return "http://www.taobao.com";
        }

    }
}
