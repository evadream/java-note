/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.generic;

/**
 * Woman
 *
 * @author huangtao
 * @date 2019/6/21
 * desc：
 */
public interface Woman {
    default String getSex() {
        return "女";
    }
}

    