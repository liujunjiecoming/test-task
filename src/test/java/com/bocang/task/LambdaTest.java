package com.bocang.task;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LJJ
 * @version 1.0.0
 * @Description lambda
 * @date 2021/4/20 上午10:22
 */
public class LambdaTest {

    @Test
    public void test() {
        Arrays.asList("a", "bc", "D").forEach(System.out::println);

        Arrays.asList("a", "b", "c").sort(String::compareTo);

    }

}

@FunctionalInterface
interface Functional {
    void method();

    default void defaultMethod() {

    }
}
