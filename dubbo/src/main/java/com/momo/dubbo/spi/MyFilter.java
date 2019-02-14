/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.dubbo.spi;

import com.alibaba.dubbo.rpc.*;

/**
 * MyFilter
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
public class MyFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        System.out.println("+++++++:"+ result.getAttachments().size());
        return result;
    }
}

    