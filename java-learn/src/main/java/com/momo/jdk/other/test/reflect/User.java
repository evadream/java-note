/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.other.test.reflect;

import lombok.Data;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User
 *
 * @author
 * @date 2019/6/17
 * desc：
 */
@Data(staticConstructor = "of")
public class User<Y> extends BaseUser implements UserInterface<String,String> {

    @Override
    public String testT(String s) {
        return s;
    }

    @Override
    public String testB(String s) {
        return s;
    }



    private static String DESCRIBE_String = "test_user";

    private static final int DESCRIBE_INT = 32;
    private static final Integer DESCRIBE_INTEGER = 64;

    private static final short DESCRIBE_SHORT = 1;

    private static final User DESCRIBE_USER = new User();


    public Y object;


    private Integer age;
    private String name;
    private String mobile;
    private short sex;


    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = User.of();
        user.setObject("3");
        System.out.println(user.getClass());
        Field[] fields = user.getClass().getFields();
        for (Field field : fields) {
            System.out.println( field.get(user).getClass());
            System.out.println(field.getName()+field.getClass());
        }

        System.out.println(user.testT("3sfdg"));
        System.out.println(user.testB("3"));
        System.out.println(user.testB("dfhgu"));


        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        System.out.println(stringList.getClass().equals(integerList.getClass()));
        stringList.add("2");
        integerList.add(2);

        System.out.println(stringList.get(0).getClass().equals(integerList.get(0).getClass()));
        System.out.println(stringList.get(0).getClass());
        System.out.println(integerList.get(0).getClass());
        System.out.println(integerList.getClass());
        System.out.println(stringList.getClass());


        Field[] fields2 = stringList.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field.getClass());
        }

        List<?> list = new ArrayList<>();
        //list.add("sd");这句无法编译
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list,"1");
        method.invoke(list,1);
        for (Object o : list) {
            System.out.println(o.getClass());
            System.out.println(o.toString());
        }
        System.out.println(list.size());


        List<String> list2 = new ArrayList<>();
        list2.add("abc");
        Class<? extends List> listClass = list.getClass();
        Method addMethod = listClass.getMethod("add", Object.class);

        // 在 String 类型的集合中添加一个 int 类型的数据
        addMethod.invoke(list2, 10);

        System.out.println(list2.size());
        for (Object s : list2) {
            System.out.println(s.getClass());
            System.out.println(s.toString());
        }


    }

    public Object testThrowException(@NonNull Object generalType) throws NumberFormatException {
        return generalType;
    }

}

    