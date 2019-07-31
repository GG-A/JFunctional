package com.github.gg_a.function;

/**
 * Represents a function that accepts 0 argument and produces a result
 * 表示一个不接收任何参数，但返回结果的函数
 *
 * @param <R> return type  返回值类型
 */
@FunctionalInterface
public interface R0<R> {
    R $();
}
