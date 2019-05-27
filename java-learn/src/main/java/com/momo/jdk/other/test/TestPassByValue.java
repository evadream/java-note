/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TestPassByValue
 *
 * @author huangtao
 * @date 2019/5/16
 * desc：
 */
public class TestPassByValue {
    public static void main(String[] args) throws IOException {

        int num1 = 1;
        int num2 = 2;

        System.out.println("Before swap method, num1 is " +
                num1 + " and num2 is " + num2);

        // 调用swap方法
        swap(num1, num2);
        System.out.println("After swap method, num1 is " +
                num1 + " and num2 is " + num2);

        Integer d = 3;
        Integer f = 4;
        swap2(d,f);
        System.out.println("After swap2 method, d is " +
                d + " and f is " + f);
        String gg = "sdef";
        String hh = "wrtyhtu";
        swap3(gg,hh);
        System.out.println("After swap3 method, gg is " +
                gg + " and hh is " + hh);
    }

    /**
     * 交换两个变量的方法
     */
    public static void swap(int n1, int n2) {
        System.out.println("\tInside the swap method");
        System.out.println("\t\tBefore swapping n1 is " + n1
                + " n2 is " + n2);
        // 交换 n1 与 n2的值
        int temp = n1;
        n1 = n2;
        n2 = temp;

        System.out.println("\t\tAfter swapping n1 is " + n1
                + " n2 is " + n2);
    }

    /**
     * 交换两个变量的方法
     */
    public static void swap2(Integer n1, Integer n2) {
        System.out.println("\tInside the swap method");
        System.out.println("\t\tBefore swapping n1 is " + n1
                + " n2 is " + n2);
        // 交换 n1 与 n2的值
        Integer temp = n1;
        n1 = n2;
        n2 = temp;
        System.out.println("\t\tAfter swapping n1 is " + n1
                + " n2 is " + n2);
    }
    /**
     * 交换两个变量的方法
     */
    public static void swap3(String n1, String n2) {
        System.out.println("\tInside the swap method");
        System.out.println("\t\tBefore swapping n1 is " + n1
                + " n2 is " + n2);
        // 交换 n1 与 n2的值
        String temp = n1;
        n1 = n2;
        n2 = temp;
        System.out.println("\t\tAfter swapping n1 is " + n1
                + " n2 is " + n2);
    }

}

    