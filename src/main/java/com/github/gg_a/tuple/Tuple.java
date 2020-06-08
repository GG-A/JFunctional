/*
 * Copyright 2019 GG-A, <2018158885@qq.com, https://github.com/GG-A/JFunctional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gg_a.tuple;

/**
 * Tuple Interface
 */
public interface Tuple {
    /**
     * The size of this Tuple.<br>
     * 元组的参数数量
     * @return 参数数量
     */
    int arity();

    /**
     * Get tuple element value by alias, it will return `null` when alias not found.<br>
     * 通过别名获取元组元素的值，如果不存在该别名，将抛出异常 AliasNotFoundException。<br>
     * 注：`_`（下划线） 在 Java 9 中被定义成了关键字，无法单独使用 `_`（下划线） 作为标识符。
     * @param alias tuple element alias 别名
     * @param <R> return type 返回值类型
     * @return tuple element value
     * @throws AliasNotFoundException if the `alias` not found. 如果元组不存在该别名，抛出异常
     */
    <R> R __(String alias);

    /**
     * The nth element of this tuple<br>
     * 从元组中取第n个元素
     * @param n index 序号
     * @param <R> return type 返回值类型
     * @return return value of sp
     * @throws IndexOutOfBoundsException if the `n` is out of range(n &lt; 0 || n &gt;= arity).
     *              当 n &lt; 0 或者 n &gt;= arity() 时，抛出异常。
     */
    <R> R element(int n);

    /**
     * The nth element with alias of this tuple<br>
     * 从元组中取第n个元素（带别名）
     * @param n index 序号
     * @param <R> return type 返回值类型
     * @return (alias, element)
     * @throws IndexOutOfBoundsException if the `n` is out of range(n &lt; 0 || n &gt;= arity).
     *              当 n &lt; 0 或者 n &gt;= arity() 时，抛出异常。
     */
    <R> Tuple2<String, R> elementWithAlias(int n);

    /**
     * Create empty tuple<br>
     * 创建一个空元组
     */
    static Tuple0 empty() {
        return Tuple0.instance();
    }

    /**
     * Create empty tuple<br>
     * 创建一个空元组
     */
    static Tuple0 of() {
        return Tuple0.instance();
    }

    /**
     * Create a tuple of 1 element<br>
     * 创建1个元素的元组
     */
    static <T1> Tuple1<T1> of(T1 _1) {
        return new Tuple1<>(_1);
    }

    /**
     * Create a tuple of 2 elements<br>
     * 创建2个元素的元组
     */
    static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
        return new Tuple2<>(_1, _2);
    }

    /**
     * Create a tuple of 3 elements<br>
     * 创建3个元素的元组
     */
    static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }

    /**
     * Create a tuple of 4 elements<br>
     * 创建4个元素的元组
     */
    static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<>(_1, _2, _3, _4);
    }

    /**
     * Create a tuple of 5 elements<br>
     * 创建5个元素的元组
     */
    static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5<>(_1, _2, _3, _4, _5);
    }

    /**
     * Create a tuple of 6 elements<br>
     * 创建6个元素的元组
     */
    static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6<>(_1, _2, _3, _4, _5, _6);
    }

    /**
     * Create a tuple of 7 elements<br>
     * 创建7个元素的元组
     */
    static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7<>(_1, _2, _3, _4, _5, _6, _7);
    }

    /**
     * Create a tuple of 8 elements<br>
     * 创建8个元素的元组
     */
    static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    /**
     * Create a tuple of 9 elements<br>
     * 创建9个元素的元组
     */
    static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        return new Tuple9<>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

}
