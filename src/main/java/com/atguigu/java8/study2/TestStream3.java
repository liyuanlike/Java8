package com.atguigu.java8.study2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.atguigu.java8.study2.Employee.Status;

/**
 * Stream的操作步骤：
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStream3 {

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
    //4).归约
    //5).收集

    /**
     * reduce(T identity, BinaryOperator<T> accumulator)/reduce(BinaryOperator<T> accumulator)：可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);

        System.out.println("------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());
    }

    private static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    //需求：搜索名字中“六”出现的次数
    @Test
    public void test2() {
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStream3::filterCharacter)
                .map((ch) -> {
                    if (ch.equals('六'))
                        return 1;
                    else
                        return 0;
                })
                .reduce(Integer::sum);

        System.out.println(sum.get());
    }

    /**
     * collect(Collector<? super T, A, R> collector)：将流转换为其他形式
     */
    @Test
    public void test3() {
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    /**
     * 最值、求和、平均值、总数
     */
    @Test
    public void test4() {
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));

        System.out.println(max.get());

        Optional<Employee> op = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(op.get());

        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);

        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        Long count = emps.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        System.out.println("------------------------------");

        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getMax());
    }

    /**
     * 分组
     */
    @Test
    public void test5() {
        Map<Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);
    }

    /**
     * 多级分组
     */
    @Test
    public void test6() {
        Map<Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    /**
     * 分区
     */
    @Test
    public void test7() {
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        System.out.println(map);
    }

    @Test
    public void test8() {
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("，", "---", "---"));

        System.out.println(str);
    }

    @Test
    public void test9() {
        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));

        System.out.println(sum.get());
    }

}
