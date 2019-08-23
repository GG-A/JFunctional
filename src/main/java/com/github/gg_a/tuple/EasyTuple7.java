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
 * A tuple of 7 same type elements<br>
 * 具有相同类型的7个元素的元组
 */
public class EasyTuple7<T> extends Tuple7<T, T, T, T, T, T, T> {

    public EasyTuple7(T _1, T _2, T _3, T _4, T _5, T _6, T _7) {
        super(_1, _2, _3, _4, _5, _6, _7);
    }

    @Override
    public T __(String alias) {
        return (T)super.__(alias);
    }

    @Override
    public EasyTuple7<T> alias(String alias1, String alias2, String alias3, String alias4, String alias5, String alias6, String alias7) {
        return (EasyTuple7<T>)super.alias(alias1, alias2, alias3, alias4, alias5, alias6, alias7);
    }

    @Override
    public T element(int n) {
        return (T)super.element(n);
    }

    @Override
    public Tuple2<String, T> elementWithAlias(int n) {
        return (Tuple2<String, T>)super.elementWithAlias(n);
    }
}