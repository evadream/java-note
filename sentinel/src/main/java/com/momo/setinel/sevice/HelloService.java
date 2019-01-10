/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.setinel.sevice;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * HelloService
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
public class HelloService {
    @SentinelResource("HelloWorld")
    public static void sayHello() {
        System.out.println("hello word 1111");
    }


}

