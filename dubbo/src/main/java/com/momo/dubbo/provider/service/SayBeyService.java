/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.momo.dubbo.provider.ISayBeyService;

/**
 * SayBeyService
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
@Service
public class SayBeyService implements ISayBeyService {
    @Override
    public String sayHello() {
        return "泰迪就是狗啊，有啥疑问么？？？";
    }

    @Override
    public String sayHello(String word) {
        return "泰迪就是狗啊，有啥疑问么？？？: " + word;
    }

}

    