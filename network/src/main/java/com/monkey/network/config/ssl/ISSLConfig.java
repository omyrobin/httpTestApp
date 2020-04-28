package com.monkey.network.config.ssl;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-28 07:37
 */
public interface ISSLConfig {

    //获取https认证状态
    SSLState getSSLState();

    String getClientCer();

    String getClientPassWord();

    String getServerCer();


}
