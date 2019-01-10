package com.momo.setinel;/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SentinelApplication
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SentinelApplication.class);
        springApplication.run(args);
    }
}

    