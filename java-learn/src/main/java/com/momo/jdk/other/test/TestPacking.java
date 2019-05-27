/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test;

/**
 * TestPacking
 *
 * @author huangtao
 * @date 2019/5/27
 * desc：吃扒鸡迷茫的装箱拆箱问题
 */
public class TestPacking {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;

        Long l3 = 3L;

        System.out.println(c == d);
        System.out.println(e == f);//为啥这个不等于？？
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));//为啥这两句都为true？？？
        System.out.println(l3.equals(a + b));
        System.out.println(l3 == (a + b));
    }

}

    