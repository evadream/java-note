/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test;

import java.io.*;

/**
 * JavaStream
 *
 * @author huangtao
 * @date 2019/5/16
 * desc：
 */
public class TestJavaStream {
    public static void main(String[] args) throws IOException {
        File f = new File("C:/java/hello");
        InputStream f2 = new FileInputStream("");



        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.read());
//        System.out.println(bufferedReader.lines());

//        bufferedReader.close();
        char d;
        //读取控制台输入字符串直到输入了c字符
        do{
            d= (char) bufferedReader.read();
            System.out.write(d);
        }while (d!='c');
    }

}

    