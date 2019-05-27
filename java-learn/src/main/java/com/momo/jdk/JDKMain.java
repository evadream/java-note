/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk;

/**
 * JDKMain
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
public class JDKMain {

    public static void main(String[] args) {
        Long mel = 10011L;
        Long tst = 00010L;

        System.out.println(mel & tst);
        String c= "10000110";
        String f = "";
        int ds = Integer.valueOf(c, 2);
        int ds2 = Integer.valueOf(f, 2);
        System.out.println(ds & ds2);
    }

}

    