/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.jvm;

/**
 * Long64BitTest
 *
 * @author huangtao
 * @date 2019/5/30
 * desc：测试log类型的原子性
 */
public class Long64BitTest {
    public static volatile long test = 0L;
    private static final int THREADS_COUNT = 200;

    public static synchronized void increase() {
        test=test+1;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while ((Thread.activeCount() > 1)) {
            Thread.yield();
        }
        System.out.println(test);

    }
}

    