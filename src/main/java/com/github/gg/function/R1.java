package com.github.gg.function;

/**
 * Represents a function that accepts 1 argument and produces a result
 * 表示一个接收 1个参数 并返回结果的函数
 *
 * @param <T> first param type  第1个参数类型
 * @param <R> return type  返回值类型
 */
@FunctionalInterface
public interface R1<T, R> {
    R $(T t);
}
