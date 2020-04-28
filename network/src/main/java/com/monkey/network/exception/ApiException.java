package com.monkey.network.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.io.NotSerializableException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import retrofit2.HttpException;

/**
 * Created by Android Studio.
 * User: omyrobin
 * Date: 2019/11/21
 * Time: 17:11
 */
public class ApiException {


    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    public static HttpThrowable handleException(Throwable e) {
        HttpThrowable et;
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case ApiException.UNAUTHORIZED:
                    et = new HttpThrowable(UNAUTHORIZED,e.getMessage());
                    break;
                case ApiException.FORBIDDEN:
                    et = new HttpThrowable(FORBIDDEN,e.getMessage());
                    break;
                case ApiException.NOT_FOUND:
                    et = new HttpThrowable(NOT_FOUND,e.getMessage());
                    break;
                case ApiException.REQUEST_TIMEOUT:
                    et = new HttpThrowable(REQUEST_TIMEOUT,e.getMessage());
                    break;
                case ApiException.INTERNAL_SERVER_ERROR:
                    et = new HttpThrowable(INTERNAL_SERVER_ERROR,e.getMessage());
                    break;
                case ApiException.BAD_GATEWAY:
                    et = new HttpThrowable(BAD_GATEWAY,e.getMessage());
                    break;
                case ApiException.SERVICE_UNAVAILABLE:
                    et = new HttpThrowable(SERVICE_UNAVAILABLE,e.getMessage());
                    break;
                case ApiException.GATEWAY_TIMEOUT:
                    et = new HttpThrowable(GATEWAY_TIMEOUT,e.getMessage());
                    break;
                default:
                    et = new HttpThrowable(HTTP_ERROR,e.getMessage());
                    break;
            }
            return et;
        } else if (e instanceof UnknownHostException) {
            et = new HttpThrowable(UNKNOWNHOST_ERROR,e.getMessage());
            return et;
        } else if (e instanceof SocketTimeoutException) {
            et = new HttpThrowable(CONNECT_TIMEOUT_ERROR, e.getMessage());
            return et;
        } else if (e instanceof InterruptedIOException) {
            et = new HttpThrowable(INTERRUPTED_ERROR, e.getMessage());
            return et;
        } else if (e instanceof ParseException) {
            et = new HttpThrowable(URL_PARSE_ERROR,e.getMessage());
            return et;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof NotSerializableException) {
            et = new HttpThrowable(JSON_PARSE_ERROR, e.getMessage());
            return et;
        } else if (e instanceof SSLHandshakeException) {
            et = new HttpThrowable(SSL_HANDSHAKE_ERROR, e.getMessage());
            return et;
        } else if (e instanceof SSLPeerUnverifiedException) {
            et = new HttpThrowable(SSL_PEER_UNVERIFIED_ERROR, e.getMessage());
            return et;
        } else {
            et = new HttpThrowable(UNKNOWN_ERROR,e.getMessage());
            return et;
        }
    }


    /**
     * http异常
     **/
    public static final int HTTP_ERROR = 1000;

    /**
     * JSON数据解析数据失败
     **/
    public static final int URL_PARSE_ERROR = 1001;

    /**
     * JSON数据解析数据失败
     **/
    public static final int JSON_PARSE_ERROR = 1002;

    /**
     * 网络中断 DNS服务器故障 域名解析劫持 触发该异常
     */
    public static final int UNKNOWNHOST_ERROR = 1003;

    /**
     * 带宽低、延迟高 路径拥堵、服务端负载吃紧 路由节点临时异常
     */
    public static final int CONNECT_TIMEOUT_ERROR = 1005;

    /**
     * 请求读写阶段，请求线程被中断 触发该异常
     */
    public static final int INTERRUPTED_ERROR = 1006;

    /**
     * Tls协议协商失败 握手失败 客户端认证证书无法通过 ---可以降级HTTP
     **/
    public static final int SSL_HANDSHAKE_ERROR = 1007;

    /**
     * 使用 HostnameVerifier 来验证 host 是否合法，如果不合法会抛出 SSLPeerUnverifiedException
     **/
    public static final int SSL_PEER_UNVERIFIED_ERROR = 1008;

    /**
     * 未知错误
     **/
    public static final int UNKNOWN_ERROR = 1010;


    /**
     * 请求到达服务端 后端服务器异常
     */
    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(int code, String message) {
            super(message);
            this.message = message;
        }
    }


    /**
     * 网络通信过程中的异常
     */
    public static class HttpThrowable extends Exception {
        public int code;
        public String message;

        public HttpThrowable(int code, String message) {
            super(message);
            this.code = code;
            this.message = message;
        }
    }
}
