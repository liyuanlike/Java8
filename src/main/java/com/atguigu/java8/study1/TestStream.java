package com.atguigu.java8.study1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Stream的操作步骤：
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStream {

    //1.创建Stream
    @Test
    public void test1() {
        //1).Collection提供了两个方法stream()与parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();//获取一个顺序流
        Stream<String> parallelStream = list.parallelStream();//获取一个并行流

        //2).通过Arrays中的stream(T[] array)获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3).通过Stream类中静态方法of(T... values)
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        //4).创建无限流
        //迭代iterate(final T seed, final UnaryOperator<T> f)
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成generate(Supplier<? extends T> s)
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 23, 5555.55),
            new Employee(102, "李四", 64, 6666.66),
            new Employee(103, "王五", 35, 4444.44),
            new Employee(104, "赵六", 56, 7777.77),
            new Employee(104, "赵六", 56, 7777.77),
            new Employee(104, "赵六", 56, 7777.77),
            new Employee(105, "田七", 47, 3333.33)
    );

    //2.中间操作
    //1).筛选与切片

    /**
     * filter(Predicate<? super T> predicate)：从流中排除某些元素
     */
    @Test
    public void test2() {
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() <= 35;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    /**
     * limit(long maxSize)：截断流，使其元素不超过给定数量
     */
    @Test
    public void test3() {
        emps.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() >= 5000;
                })
                .limit(3)
                .forEach(System.out::println);
    }

    /**
     * skip(long n)：跳过元素，返回一个扔掉了前n个元素的流；若流中元素不足n个，则返回一个空流；与limit(long maxSize)互补
     */
    @Test
    public void test4() {
        emps.parallelStream()
                .filter((e) -> e.getSalary() >= 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * distinct()：筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */
    @Test
    public void test5() {
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

}
