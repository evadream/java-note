/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.util;

import lombok.Cleanup;
import lombok.val;
import lombok.var;

import java.io.*;
import java.util.ArrayList;

/**
 * Beanutil
 *
 * @author huangtao
 * @date 2019/6/17
 * desc：
 */

public class LombokTest {

    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream("/Users/momo/document/书籍/Java性能优化权威指南.pdf");
        @Cleanup OutputStream out = new FileOutputStream("/Users/momo/document/书籍/test.pdf");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }


    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {

        var list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (var s : list) {
            System.out.println(s);
        }
    }

}

    