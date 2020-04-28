package com.omyrobin.httptestapp.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 20:00
 */
public class UserTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();
        String token = headers.get("usertoken");
        if(TextUtils.isEmpty(token)){
            String userToken = getToken();
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("usertoken", userToken)
                    .build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);
    }

    private String getToken(){
        return "token";
    }

}
