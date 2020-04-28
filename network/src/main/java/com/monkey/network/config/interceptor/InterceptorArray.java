package com.monkey.network.config.interceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-28 11:48
 */
public class InterceptorArray {

    public static List<Interceptor> interceptors = new ArrayList<>();

    public static List<Interceptor> networkInterceptors = new ArrayList<>();
}
