package com.momo.dubbo;/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */

import com.momo.dubbo.provider.IHelloService;
import com.momo.dubbo.provider.service.HelloService;
import com.momo.dubbo.provider.service.SayBeyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DemoApplication
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.momo.dubbo");

        IHelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.sayHello());
        IHelloService sayHello = context.getBean(HelloService.class);
        while (true) {
            System.out.println(sayHello.sayHello());
        }
//        synchronized (DemoApplication.class) {
//            while (true) {
//                DemoApplication.class.wait();
//            }
//        }

    }

}

    