package com.monkey.network.config.okhttp;

import com.monkey.network.config.ssl.SSLState;

/**
 * @Description: 网络请求库的基本配置信息
 * @Author: omyrobin
 * @CreateDate: 2020-04-26 11:07
 */
public class DefaultOkConfig extends OkConfig {
    //连接超时时间
    public static final int DEFAULT_CONNECT_TIMEOUT = 30;
    //写入超时时间
    public static final int DEFAULT_WRITE_TIMEOUT = 30;
    //读取超时时间
    public static final int DEFAULT_READ_TIMEOUT = 30;
    //缓存文件夹
    public static final String DEFAULT_CACHEDIR = "/Monkey/network";
    //缓存大小
    public static final int DEFAULT_CACHESIZE = 1024 * 1024 * 10;

    @Override
    public int getConnectTimeOut() {
        return DEFAULT_CONNECT_TIMEOUT;
    }

    @Override
    public int getWriteTimeOut() {
        return DEFAULT_WRITE_TIMEOUT;
    }

    @Override
    public int getReadTimeOut() {
        return DEFAULT_READ_TIMEOUT;
    }

    @Override
    public String getCacheDirPath() {
        return DEFAULT_CACHEDIR;
    }

    @Override
    public int getCacheSize() {
        return DEFAULT_CACHESIZE;
    }

    /**
     *
     * @return 枚举 https 认证状态
     */
    @Override
    public SSLState getSSLState() {
        return null;
    }

    /**
     *
     * @return 客户端证书asset中的名称
     */
    @Override
    public String getClientCer() {
        return null;
    }

    /**
     *
     * @return 客户端证书密码（出于安全考虑 建议使用gradle buildConfig 自定义属性获取）
     */
    @Override
    public String getClientPassWord() {
        return null;
    }

    /**
     *
     * @return 服务端证书asset中的名称
     */
    @Override
    public String getServerCer() {
        return null;
    }


}
