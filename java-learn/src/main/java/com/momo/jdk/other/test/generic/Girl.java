/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.generic;

import com.momo.jdk.other.test.reflect.BaseUser;

/**
 * Girl
 *
 * @author huangtao
 * @date 2019/6/21
 * desc：
 */
public class Girl extends BaseUser implements Human, Woman {

    @Override
    public String setName(String name) {
        return name;
    }

}

    