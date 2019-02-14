/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.spi;

import java.util.ServiceLoader;

/**
 * SPITest
 *
 * @author huangtao
 * @date 2019/2/9
 * desc：
 */
public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<SPI> serviceLoader = ServiceLoader.load(SPI.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(SPI::getExtension);
    }
}

    