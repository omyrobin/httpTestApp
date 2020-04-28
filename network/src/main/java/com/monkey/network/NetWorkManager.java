package com.monkey.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.monkey.network.config.okhttp.DefaultOkConfig;
import com.monkey.network.config.okhttp.OkConfig;
import com.monkey.network.config.retrofit.IRerofitConfig;
import com.monkey.network.config.ssl.SSLState;
import com.monkey.network.config.ssl.TrustAllCerts;
import com.monkey.network.config.ssl.TrustDoubleCerts;
import com.monkey.network.config.ssl.TrustHostnameVerifier;
import com.monkey.network.config.ssl.TrustSingleCerts;
import com.omyrobin.network.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-26 10:47
 */
public class NetWorkManager {

    private static final String TAG = NetWorkManager.class.getSimpleName();

    private static volatile NetWorkManager netWorkManager;

    private OkHttpClient.Builder okBuilder;

    private Retrofit retrofit;

    private NetWorkManager() {
        okBuilder = new OkHttpClient.Builder();
    }

    public static NetWorkManager getInstance() {
        if (netWorkManager == null) {
            synchronized (NetWorkManager.class) {
                if (netWorkManager == null) {
                    netWorkManager = new NetWorkManager();
                }
            }
        }
        return netWorkManager;
    }

    public NetWorkManager init(Context context, IRerofitConfig urlConfig) {
        return init(context, new DefaultOkConfig(), urlConfig);
    }

    public NetWorkManager init(Context context, OkConfig okConfig, IRerofitConfig urlConfig) {

        if(context == null) {
            throw new IllegalStateException("context not be null");
        }

        if (retrofit != null) {
            throw new IllegalStateException("Already exists， You can initialize it once");
        }

        //如果业务层传递OkConfig未空 使用默认OkConfig
        if (okConfig == null) {
            okConfig = new DefaultOkConfig();
        }

        if (okConfig.getConnectTimeOut() > 0) {
            okBuilder.connectTimeout(okConfig.getConnectTimeOut(), TimeUnit.SECONDS);
        } else {
            throw new IllegalArgumentException("connectTimeOut must be greater than 0");
        }

        if (okConfig.getWriteTimeOut() > 0) {
            okBuilder.writeTimeout(okConfig.getWriteTimeOut(), TimeUnit.SECONDS);
        } else {
            throw new IllegalArgumentException("writeTimeout must be greater than 0");
        }

        if (okConfig.getReadTimeOut() > 0) {
            okBuilder.readTimeout(okConfig.getReadTimeOut(), TimeUnit.SECONDS);
        } else {
            throw new IllegalArgumentException("readTimeout must be greater than 0");
        }

        if (!TextUtils.isEmpty(okConfig.getCacheDirPath())) {
            File cacheFile = new File(context.getExternalCacheDir() + okConfig.getCacheDirPath());
            if (okConfig.getCacheSize() > 1024 * 1024) {
                okBuilder.cache(new Cache(cacheFile, okConfig.getCacheSize()));
            } else {
                throw new IllegalArgumentException("cacheSize must be greater than 1MB");
            }

        } else {
            throw new NullPointerException("cacheDirPath not be null");
        }

        if (okConfig.getInterceptors() != null && okConfig.getInterceptors().size() > 0) {
            for (Interceptor interceptor : okConfig.getInterceptors()) {
                if (interceptor != null) {
                    okBuilder.addInterceptor(interceptor);
                }
            }
        }

        if (okConfig.getNetworkInterceptors() != null && okConfig.getNetworkInterceptors().size() > 0) {
            for (Interceptor interceptor : okConfig.getNetworkInterceptors()) {
                if (interceptor != null) {
                    okBuilder.addNetworkInterceptor(interceptor);
                }
            }
        }

        //添加网络日志
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG,message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addNetworkInterceptor(logInterceptor);

        if(okConfig.getSSLState() != null){
            //支持信任所有证书https
            if(okConfig.getSSLState() == SSLState.ALL){
                okBuilder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory(), new TrustAllCerts());
            }


            //支持https信任指定服务器的证书---单向认证
            if(okConfig.getSSLState() == SSLState.SINGLE){
                try {
                    SSLContext sslContext =  new TrustSingleCerts().setCertificates(context.getAssets().open(okConfig.getServerCer()));
                    okBuilder.sslSocketFactory(sslContext.getSocketFactory(),new TrustSingleCerts());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //支持https信任指定服务器的证书 服务器同时验证客户端证书 ---双向认证
            if(okConfig.getSSLState() == SSLState.DOUBLE) {
                try {
                    KeyStore clientKeyStore = KeyStore.getInstance("BKS");
                    clientKeyStore.load(context.getAssets().open(okConfig.getClientCer()), okConfig.getClientPassWord().toCharArray());
                    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    keyManagerFactory.init(clientKeyStore, okConfig.getClientPassWord().toCharArray());
                    SSLContext sslContext =  new TrustDoubleCerts().setCertificates(keyManagerFactory, context.getAssets().open(okConfig.getServerCer()));
                    okBuilder.sslSocketFactory(sslContext.getSocketFactory(),new TrustDoubleCerts());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            okBuilder.hostnameVerifier(new TrustHostnameVerifier());
        }
        //重试
        okBuilder.retryOnConnectionFailure(true);
        OkHttpClient client = okBuilder.build();


        if (urlConfig == null) {
            throw new IllegalArgumentException("You must be Implement IRerofitConfig");
        }

        if (!TextUtils.isEmpty(urlConfig.getBaseUrl())) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(urlConfig.getBaseUrl())
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            throw new NullPointerException("baseUrl not be null");
        }
        return netWorkManager;
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            throw new NullPointerException("NetWorkManager must be init");
        }
        return retrofit;
    }

    public <T> T createService(Class<T> service) {
        if (retrofit == null) {
            throw new NullPointerException("NetWorkManager must be init");
        }
        return retrofit.create(service);
    }
}
