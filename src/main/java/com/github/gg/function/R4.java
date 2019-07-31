package com.github.gg.function;

/**
 * Represents a function that accepts 4 arguments and produces a result
 * 表示一个接收 4个参数 并返回结果的函数
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * @param <R>
 */
@FunctionalInterface
public interface R4<T1, T2, T3, T4, R> {
    R $(T1 t1, T2 t2, T3 t3, T4 t4);
}
