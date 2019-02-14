/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.spi;

/**
 * HelloSpi
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
public class HelloSPI implements SPI {
    @Override
    public SPI getExtension() {
        System.out.println("这是 HelloSPI ～～～～～～～");
        return new HelloSPI();
    }
}

    