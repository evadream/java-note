/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.generic;


import java.time.ZoneId;

/**
 * TestUser
 *
 * @author huangtao
 * @date 2019/6/21
 * desc：
 */
public class User<Y extends Woman> {
    private Y y;

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public <Z extends Girl & Human & Woman> Z testUpperBound(Z z) {
        System.out.println(z.getSex());
        return z;
    }

    public void testFuntionObject(Y z, TestFunctionObject girl) {
        girl.compare(z);
    }

    public static void main(String[] args) {
        User<? super Woman> user = new User();
        Girl girl = new Girl();
        user.setY(girl);
        Girl getGirl = (Girl) user.getY();//必须通过强转才能正常取
        System.out.println(getGirl.getSex());

        User u = new User();
        u.setY(girl);

        User<Girl>[] girls1 = new User[10];
        User<Woman>[] girls2 = new User[10];
        Object[] objects = girls1;
        objects[0] = u;

        girls1[0].getY();
        for (User<Girl> girlUser : girls1) {
            girlUser.getY().getSex();
        }
    }

    public class TestFunctionObject<T> {
        public void compare(T t) {
            System.out.println(t);
        }
    }

}

    