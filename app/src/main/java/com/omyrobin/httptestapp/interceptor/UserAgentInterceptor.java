package com.omyrobin.httptestapp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 19:59
 */
public class UserAgentInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .header("User-Agent", "userAgent")
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
