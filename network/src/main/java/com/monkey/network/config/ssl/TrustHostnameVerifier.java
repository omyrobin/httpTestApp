package com.monkey.network.config.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 19:08
 */
public class TrustHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}