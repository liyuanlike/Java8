package com.atguigu.java8.study2;

import org.junit.Test;

public class TestDefaultInterface {

    @Test
    public void test() {
        SubClass sc = new SubClass();
        System.out.println(sc.getName());

        MyInterface.show();
    }

}
