/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.spi;

/**
 * Meal
 *
 * @author huangtao
 * @date 2019/4/6
 * desc：
 */
public enum Meal {
    APPTIZER(Food.Appetizer.class), MAINCOURSE(Food.MainCourse.class);

    public interface Food {
        enum Appetizer implements Food {
            SALAD, SOUP;
        }

        enum MainCourse implements Food {
            Dumpling, Noodles;
        }
    }

    private Food[] values;

    private Meal(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    void show() {
        for (Food value : values) {
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        System.out.println(Meal.APPTIZER.name());
    }
}

    