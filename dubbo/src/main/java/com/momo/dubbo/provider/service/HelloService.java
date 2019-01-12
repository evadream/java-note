/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.dubbo.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.dubbo.config.annotation.Service;
import com.momo.dubbo.provider.IHelloService;
import org.springframework.stereotype.Component;

/**
 * HelloService
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
@Component
@Service
public class HelloService implements IHelloService {
    @SentinelResource(value = "sayHello")
    @Override
    public String sayHello() {
        return "hello dubbo";
    }
}

    