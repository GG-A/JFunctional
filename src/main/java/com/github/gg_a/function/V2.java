package com.github.gg_a.function;

/**
 * Represents a function that accepts 2 arguments and returns no result
 * 表示一个接收 2个参数 不返回任何结果的函数
 *
 * @param <T1> first param type  第1个参数类型
 * @param <T2> second param type  第2个参数类型
 */
@FunctionalInterface
public interface V2<T1, T2> {
    void $(T1 t1, T2 t2);
}
