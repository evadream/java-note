/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test;

import java.util.Properties;
import java.util.Scanner;

/**
 * ScannerDemo
 *
 * @author huangtao
 * @date 2019/5/17
 * desc：
 */
public class ScannerDemo {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(properties);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
//           System.out.println(scanner.next());

        }
    }
}

    