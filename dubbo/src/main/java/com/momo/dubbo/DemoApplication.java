package com.momo.dubbo;/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DemoApplication
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
@SpringBootApplication(scanBasePackages = "com.momo.dubbo")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

    