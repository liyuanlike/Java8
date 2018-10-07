package com.atguigu.java8.study2;

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
public class TestStream1 {

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
    //2).映射
    //3).排序

    /**
     * map(Function<? super T, ? extends R> mapper)：将流中的每个元素都转换成新的元素或提取信息
     * flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)：将流中的每个元素都转换成新的流，然后把所有流连接成一个流
     */
    @Test
    public void test1() {
        Stream<String> str = emps.stream().map((e) -> e.getName());

        System.out.println("------------------------------");

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        Stream<String> stream = strList.stream().map(String::toUpperCase);

        stream.forEach(System.out::println);

        Stream<Stream<Character>> stream2 = strList.stream().map(TestStream1::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("------------------------------");

        Stream<Character> stream3 = strList.stream().flatMap(TestStream1::filterCharacter);

        stream3.forEach(System.out::println);
    }

    private static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * sorted()：自然排序
     * sorted(Comparator<? super T> comparator)：定制排序
     */
    @Test
    public void test2() {
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------");

        emps.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                })
                .forEach(System.out::println);
    }

}
