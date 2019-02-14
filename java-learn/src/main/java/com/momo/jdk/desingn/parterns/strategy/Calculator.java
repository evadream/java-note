/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.desingn.parterns.strategy;

/**
 * Calculator
 *
 * @author huangtao
 * @date 2019/1/26
 * desc：
 */
public enum Calculator {
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },

    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a-b;
        }
    };

    private String value;

    public String getValue() {
        return value;
    }

    Calculator(String value) {
        this.value = value;
    }

    public abstract int exec(int a, int b);


    public static void main(String[] args) {

        System.out.println(Calculator.ADD.exec(1,2));
        System.out.println(Calculator.SUB.exec(1,2));

    }
}

    