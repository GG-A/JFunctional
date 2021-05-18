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
 * A tuple of 6 same type elements<br>
 * 具有相同类型的6个元素的元组
 * @since 0.1.1
 */
public class EasyTuple6<T> extends Tuple6<T, T, T, T, T, T> {
    private static final long serialVersionUID = 10065918016L;

    public EasyTuple6(T _1, T _2, T _3, T _4, T _5, T _6) {
        super(_1, _2, _3, _4, _5, _6);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T __(TupleAlias alias) {
        return super.__(alias);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T __(String alias) {
        return super.__(alias);
    }

    @Override
    public EasyTuple6<T> alias(TupleAlias... aliases) {
        return (EasyTuple6<T>)super.alias(aliases);
    }

    @Override
    public EasyTuple6<T> alias(String... aliases) {
        return (EasyTuple6<T>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T element(int n) {
        return super.element(n);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<TupleAlias, T> elementWithTupleAlias(int n) {
        return super.elementWithTupleAlias(n);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<String, T> elementWithAlias(int n) {
        return super.elementWithAlias(n);
    }

}
