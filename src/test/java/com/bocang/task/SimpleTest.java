package com.bocang.task;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 上午9:03 20-12-10
 */
public class SimpleTest {

    @Test
    public void test3() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");

        Optional<String> max = list.stream().min(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    @Test
    public void test1() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 20, "male", "New York"));
        personList.add(new Person("Jack", 7000, 30, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 18, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 40, "female", "New York"));
        personList.add(new Person("Owen", 9500, 10, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 60, "female", "New York"));

//        List<String> filterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
//        System.out.println(filterList);
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + max.get().getSalary());
    }

}

class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
    // 省略了get和set，请自行添加


    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getArea() {
        return area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
