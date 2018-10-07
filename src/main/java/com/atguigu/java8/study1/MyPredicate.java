package com.atguigu.java8.study1;

@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

}
