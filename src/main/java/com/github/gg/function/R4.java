package com.github.gg.function;

/**
 * Represents a function that accepts 4 arguments and produces a result
 * 表示一个接收 4个参数 并返回结果的函数
 *
 * @param <T1> first param type  第1个参数类型
 * @param <T2> second param type  第2个参数类型
 * @param <T3> third param type  第3个参数类型
 * @param <T4> fourth param type  第4个参数类型
 * @param <R> return type  返回值类型
 */
@FunctionalInterface
public interface R4<T1, T2, T3, T4, R> {
    R $(T1 t1, T2 t2, T3 t3, T4 t4);
}
