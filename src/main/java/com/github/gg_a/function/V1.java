package com.github.gg_a.function;

/**
 * Represents a function that accepts 1 argument and returns no result
 * 表示一个接收 1个参数 不返回任何结果的函数
 *
 * @param <T> first param type  第1个参数类型
 */
@FunctionalInterface
public interface V1<T> {
    void $(T t);
}
