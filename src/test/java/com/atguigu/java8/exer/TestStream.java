package com.atguigu.java8.exer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.atguigu.java8.study2.Employee;
import com.atguigu.java8.study2.Employee.Status;
import org.junit.Test;

public class TestStream {

    //1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？比如：给定[1,2,3,4,5]，返回[1,4,9,16,25]
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 64, 6666.66, Status.BUSY),
            new Employee(101, "张三", 23, 5555.55, Status.FREE),
            new Employee(103, "王五", 35, 4444.44, Status.VOCATION),
            new Employee(104, "赵六", 56, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 56, 7777.77, Status.FREE),
            new Employee(104, "赵六", 56, 7777.77, Status.FREE),
            new Employee(105, "田七", 47, 3333.33, Status.BUSY)
    );

    //2.怎样用map(Function<? super T, ? extends R> mapper)和reduce(BinaryOperator<T> accumulator)方法数一数流中有多少个Employee呢？
    @Test
    public void test2() {
        Optional<Integer> count = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }

}
