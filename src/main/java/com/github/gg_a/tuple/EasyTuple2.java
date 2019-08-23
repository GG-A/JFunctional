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
 * A tuple of 2 same type elements<br>
 * 具有相同类型的2个元素的元组
 */
public class EasyTuple2<T> extends Tuple2<T, T> {

    public EasyTuple2(T _1, T _2) {
        super(_1, _2);
    }

    @Override
    public T __(String alias) {
        return (T)super.__(alias);
    }

    @Override
    public EasyTuple2<T> alias(String alias1, String alias2) {
        return (EasyTuple2<T>)super.alias(alias1, alias2);
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
