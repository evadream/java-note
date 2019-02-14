/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.spi;

/**
 * Hello2Spi
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
public class Hello2SPI implements SPI {
    @Override
    public SPI getExtension() {
        System.out.println("这是 Hello2SPI Hello2SPI Hello2SPI");
        return new Hello2SPI();
    }
}

    