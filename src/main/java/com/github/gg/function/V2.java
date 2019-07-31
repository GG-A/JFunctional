package com.github.gg.function;

/**
 * Represents a function that accepts 2 arguments and returns no result
 * 表示一个接收 2个参数 不返回任何结果的函数
 * @param <T1>
 * @param <T2>
 */
@FunctionalInterface
public interface V2<T1, T2> {
    void $(T1 t1, T2 t2);
}
