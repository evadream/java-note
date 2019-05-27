/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassLoaderTest
 *
 * @author huangtao
 * @date 2019/5/23
 * desc：测试类加载器不同，类是否相等
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream is=getClass().getResourceAsStream(fileName);
                if(is==null){
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                   throw new ClassNotFoundException(name);
                }
            }
        };
        Object o = myClassLoader.loadClass("com.momo.jdk.other.test.classloader.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof com.momo.jdk.other.test.classloader.ClassLoaderTest);
        ClassLoaderTest n = new ClassLoaderTest();
        System.out.println(n instanceof com.momo.jdk.other.test.classloader.ClassLoaderTest);

    }
}

    