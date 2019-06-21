/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.reflect;

import java.lang.reflect.Method;

/**
 * Erasure
 *
 * @author huangtao
 * @date 2019/6/21
 * desc：
 */
public class Erasure<T> {
    T object;

    public Erasure(T object) {
        this.object = object;
    }

    public void add(T object) {

    }

    public static void main(String[] args) {

        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:" + eclz.getName());

        Method[] methods = eclz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(" method:" + m.toString());
        }
    }
}

    