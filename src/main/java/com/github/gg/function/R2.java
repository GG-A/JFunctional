package com.github.gg.function;

/**
 * Represents a function that accepts 2 arguments and produces a result
 * 表示一个接收 2个参数 并返回结果的函数
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
@FunctionalInterface
public interface R2<T1, T2, R> {
    R $(T1 t1, T2 t2);
}
