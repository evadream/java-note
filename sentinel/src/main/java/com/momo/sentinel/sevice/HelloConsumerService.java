/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.sentinel.sevice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.momo.dubbo.provider.IHelloService;
import com.momo.dubbo.provider.ISayBeyService;
import org.springframework.stereotype.Component;

/**
 * HelloConsumerService
 *
 * @author huangtao
 * @date 2019/1/12
 * desc：
 */
@Component("helloConsumerService")
public class HelloConsumerService {
    @Reference
    private IHelloService helloService;
    @Reference
    private ISayBeyService sayBeyService;

    public String getHello() {
        return helloService.sayHello();
    }

    public String geyHello(String word) {
        return helloService.sayHello(word);
    }

    public String getSayBey(String word) {
        return sayBeyService.sayHello(word);
    }
}

    