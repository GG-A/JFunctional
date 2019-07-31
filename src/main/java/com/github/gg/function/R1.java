package com.github.gg.function;

/**
 * Represents a function that accepts 1 argument and produces a result
 * 表示一个接收 1个参数 并返回结果的函数
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface R1<T, R> {
    R $(T t);
}
