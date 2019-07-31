package com.github.gg.function;

/**
 * Represents a function that accepts 3 arguments and returns no result
 * 表示一个接收 3个参数 不返回任何结果的函数
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
@FunctionalInterface
public interface V3<T1, T2, T3> {
    void $(T1 t1, T2 t2, T3 t3);
}
