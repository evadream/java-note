/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.sentinel.spi;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.momo.sentinel.sevice.HelloConsumerService;
import com.momo.sentinel.sevice.TestSPIDemoService;

/**
 * MyFilter
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
//@Activate(group = Constants.CONSUMER)
public class MyTestFilter implements Filter {
    private TestSPIDemoService testSPIDemoService;


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        System.out.println(testSPIDemoService.getTestSPIResult());
        return result;
    }

    public void setTestSPIDemoService(TestSPIDemoService testSPIDemoService) {
        this.testSPIDemoService = testSPIDemoService;
    }
}

    