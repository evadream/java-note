/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * VolatileTest
 *
 * @author huangtao
 * @date 2019/5/30
 * desc：
 */
public class VolatileTest {

    public static volatile AtomicInteger test = new AtomicInteger(0);
    public static volatile int test1 = 1;


    public static void increase() {
        //非线程安全
//        test1++;
        //线程安全
        test.incrementAndGet();
    }

    private static final int THREADS_COUNT = 200;

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

    