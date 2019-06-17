/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ReflectionTest
 *
 * @author huangtao
 * @date 2019/6/14
 * desc：使用反射生成bean
 */
public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        User user = User.of();
        user.setName("lili");
        user.setAge(1);
        user.setMobile("123456789");
        user.setSex((short) 1);
//        System.out.println(user.getName());

        Class clazz = Class.forName("com.momo.jdk.other.test.reflect.User");
        Method method = clazz.getMethod("setName", String.class);
        Constructor constructor = clazz.getConstructor();
        Object o = constructor.newInstance();
        method.invoke(o, "haha");

        //获取类的名称
        String clazzName = clazz.getName();
        //获取类的所有属性
        Field[] fields = clazz.getFields();
        //获取类的所有方法
        Method[] methods = clazz.getMethods();
        //获取类上的所有的注解
        Annotation[] annotations = clazz.getAnnotations();
        //获取类的构造器
        Constructor cons = clazz.getConstructor();

        for (Method meth : methods) {
            //获去方法声明的类
            System.out.println("method name:" + meth.getDeclaringClass().getName());
            //获取方法访问权限
            int modify = meth.getModifiers();
            System.out.println("method modify:" + modify);
            System.out.println("method returnType name:" + meth.getReturnType().getName());
            //获取方法范型类名称
            System.out.println("method generalReturnType name:" + meth.getGenericReturnType().getTypeName());
            //获取方法异常类型
            Class[] c = meth.getExceptionTypes();
            System.out.println("method exceptionType name:" + (c.length == 0 ? "null" : c[0].getName()));
            //获取方法参数类型
            Class[] paramTypeClazz = meth.getParameterTypes();
            System.out.println("method params types length:" + paramTypeClazz.length);
            //获取方法个数
            System.out.println("method param count:" + meth.getParameterCount());
        }

        //测试是有可以修改字段
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
            //获取字段访问权限
            int modify = field.getModifiers();
            System.out.println("modify:" + modify);
            //获取字段是否可被访问
            boolean access = field.isAccessible();
            if (!access) {
                //设置访问权限，保证对private的属性的访问（表示反射时不检查属性权限）
                //field.setAccessible(true) 只是改变了field实例的属性，没有改变object实例属性的权限
                field.setAccessible(true);
            }
            //获取字段的值
            Object value = field.get(user);
            System.out.println("before modify value:" + value.toString());
            if (field.getType().getSimpleName().equals("String")) {
                field.set(user, "test");
            } else if (field.getType().getSimpleName().equalsIgnoreCase("short")) {
                field.set(user, (short) 1);
            } else {
                field.set(user, 1);
            }

            Object valueAfterModify = field.get(user);
            System.out.println("after modify value:" + valueAfterModify.toString());
        }

        // 测试是否可以修改私有常量：不可以修改基本类型及其包装类

//        Field fieldInt = clazz.getDeclaredField("DESCRIBE_INT");
//        fieldInt.setAccessible(true);
//        System.out.println("before modify fieldInt value:" + fieldInt.get(user));
//        fieldInt.set(user, 128);
//        System.out.println("after modify fieldInt value:" + fieldInt.get(user));
//
//
//        Field fieldInteger = clazz.getDeclaredField("DESCRIBE_INTEGER");
//        fieldInteger.setAccessible(true);
//        System.out.println("before modify fieldInteger value:" + fieldInteger.get(user));
//        fieldInteger.set(user, 1024);
//        System.out.println("after modify fieldInteger value:" + fieldInteger.get(user));
//
//
//        Field fieldShort = clazz.getDeclaredField("DESCRIBE_SHORT");
//        fieldShort.setAccessible(true);
//        System.out.println("before modify fieldShort value:" + fieldShort.get(user));
//        fieldShort.set(user, (short) 2);
//        System.out.println("after modify fieldShort value:" + fieldShort.get(user));


        Field fieldUser = clazz.getDeclaredField("DESCRIBE_USER");
        fieldUser.setAccessible(true);
        Field userNameFiled = user.getClass().getDeclaredField("name");
        userNameFiled.setAccessible(true);
        System.out.println("before modify fieldUser value:" + userNameFiled.get(user));
        userNameFiled.set(user, "test test test");
        System.out.println("after modify fieldUser value:" + userNameFiled.get(user));


    }

}

    