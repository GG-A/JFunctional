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
     * The size of this Tuple.<BR/>
     * 元组的参数数量
     * @return 参数数量
     */
    int arity();

    /**
     * Get tuple element value by alias, it will return `null` when alias not found.<BR/>
     * 通过别名获取元组元素的值，如果不存在该别名，将抛出异常 AliasNotFoundException。<BR/>
     * 注：`_`（下划线） 在 Java 9 中被定义成了关键字，无法单独使用 `_`（下划线） 作为标识符。
     * @param alias tuple element alias 别名
     * @param <R> return type 返回值类型
     * @return tuple element value
     * @throws AliasNotFoundException if the `alias` not found. 如果元组不存在该别名，抛出异常
     */
    <R> R __(String alias);

    /**
     * The nth element of this tuple<BR/>
     * 从元组中取第n个元素
     * @param n index 序号
     * @return return value of sp
     * @throws IndexOutOfBoundsException if the `n` is out of range(n < 0 || n >= arity).
     *              当 n<0 或者 n>arity() 时，抛出异常。
     */
    <R> R element(int n);

    /**
     * The nth element with alias of this tuple<BR/>
     * 从元组中取第n个元素（带别名）
     * @param n
     * @return
     * @throws IndexOutOfBoundsException if the `n` is out of range(n < 0 || n >= arity).
     *              当 n<0 或者 n>arity() 时，抛出异常。
     */
    <R> Tuple2<String, R> elementWithAlias(int n);


}
