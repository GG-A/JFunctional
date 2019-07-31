package com.github.gg_a.function;

/**
 * Represents a function that accepts 3 arguments and produces a result
 * 表示一个接收 3个参数 并返回结果的函数
 *
 * @param <T1> first param type  第1个参数类型
 * @param <T2> second param type  第2个参数类型
 * @param <T3> third param type  第3个参数类型
 * @param <R> return type  返回值类型
 */
@FunctionalInterface
public interface R3<T1, T2, T3, R> {
    R $(T1 t1, T2 t2, T3 t3);
}
