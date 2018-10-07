package com.atguigu.java8.exer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.atguigu.java8.study1.Employee;

public class TestLambda {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 23, 5555.55),
            new Employee(102, "李四", 64, 6666.66),
            new Employee(103, "王五", 35, 4444.44),
            new Employee(104, "赵六", 56, 7777.77),
            new Employee(105, "田七", 47, 3333.33)
    );

    @Test
    public void test1() {
        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        emps.forEach(System.out::println);
    }

    @Test
    public void test2() {
        String trimStr = strHandler("\t\t\t 我大尚硅谷威武 ", (str) -> str.trim());
        System.out.println(trimStr);

        String upper = strHandler("abcdef", (str) -> str.toUpperCase());
        System.out.println(upper);

        String newStr = strHandler("我大尚硅谷威武", (str) -> str.substring(2, 5));
        System.out.println(newStr);
    }

    //需求：用于处理字符串
    private String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);

        op(100L, 200L, (x, y) -> x * y);
    }

    //需求：对于两个Long型数据进行处理
    private void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

}
