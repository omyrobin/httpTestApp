package com.omyrobin.httptestapp.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 11:46
 */
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
