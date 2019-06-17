/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.reflect;

import lombok.Data;

/**
 * User
 *
 * @author huangtao
 * @date 2019/6/17
 * desc：
 */
@Data
public class User<T> {

    private static String DESCRIBE_String = "test_user";

    private static final int DESCRIBE_INT = 32;
    private static final Integer DESCRIBE_INTEGER = 64;

    private static final short DESCRIBE_SHORT = 1;

    private static final User DESCRIBE_USER = new User();


    private Integer age;
    private String name;
    private String mobile;
    private short sex;

    public T testThrowException(T generalType) throws NumberFormatException {
        return generalType;
    }
}

    