package com.omyrobin.httptestapp.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: omyrobin
 * @CreateDate: 2020-04-27 14:24
 */
public class TestBean implements Serializable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
