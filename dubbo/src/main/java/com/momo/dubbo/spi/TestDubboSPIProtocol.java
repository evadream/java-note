/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * TestDubboSPI
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
public class TestDubboSPIProtocol implements Protocol{
    @Override
    public int getDefaultPort() {
        System.out.println("TestDubboSPI  getDefaultPort");

        return 0;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        System.out.println("TestDubboSPI  export");

        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
        System.out.println("TestDubboSPI  refer");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("TestDubboSPI  destroy");

    }
}

    