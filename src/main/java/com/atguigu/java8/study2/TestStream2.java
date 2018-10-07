package com.atguigu.java8.study2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import com.atguigu.java8.study2.Employee.Status;

/**
 * Stream的操作步骤：
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStream2 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 64, 6666.66, Status.BUSY),
            new Employee(101, "张三", 23, 5555.55, Status.FREE),
            new Employee(103, "王五", 35, 4444.44, Status.VOCATION),
            new Employee(104, "赵六", 56, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 56, 7777.77, Status.FREE),
            new Employee(104, "赵六", 56, 7777.77, Status.FREE),
            new Employee(105, "田七", 47, 3333.33, Status.BUSY)
    );

    //3.终止操作
    //1).匹配
    //2).查找
    //3).统计

    /**
     * allMatch()：检查是否匹配所有元素
     * anyMatch()：检查是否至少匹配一个元素
     * noneMatch()：检查是否没有匹配的元素
     */
    @Test
    public void test1() {
        boolean b = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));

        System.out.println(b);

        boolean b1 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Status.BUSY));

        System.out.println(b1);

        boolean b2 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.BUSY));

        System.out.println(b2);
    }

    /**
     * findFirst()：返回第一个元素
     * findAny()：返回当前流中的任意元素
     */
    @Test
    public void test2() {
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op.get());

        System.out.println("------------------------------");

        Optional<Employee> op2 = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Status.FREE))
                .findAny();

        System.out.println(op2.get());
    }

    /**
     * count()：返回流中元素的总个数
     * max()：返回流中最大值
     * min()：返回流中最小值
     */
    @Test
    public void test3() {
        long count = emps.stream()
                .filter((e) -> e.getStatus().equals(Status.FREE))
                .count();

        System.out.println(count);

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);

        System.out.println(op.get());

        Optional<Employee> op2 = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        System.out.println(op2.get());
    }

    //注意：流进行了终止操作后，不能再次使用
    @Test
    public void test4() {
        Stream<Employee> stream = emps.stream().filter((e) -> e.getStatus().equals(Status.FREE));

        long count = stream.count();

        stream.map(Employee::getSalary).max(Double::compare);
    }

}
