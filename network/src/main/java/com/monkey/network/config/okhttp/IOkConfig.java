package com.monkey.network.config.okhttp;

import com.monkey.network.config.ssl.SSLState;

/**
 * @Description: OkHttpClient的配置文件接口 提供业务层来实现，根据业务需要来初始化OkHttpClient
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 12:24
 */
public interface IOkConfig {

    //超时时间
    int getConnectTimeOut();
    //写入时间
    int getWriteTimeOut();
    //读取时间
    int getReadTimeOut();
    //缓存文件夹路径
    String getCacheDirPath();
    //缓存文件夹大小
    int getCacheSize();
}
