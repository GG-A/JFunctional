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
 * A tuple of 1 element<br>
 * 1个元素的元组
 *
 * @param <T> type of the 1st element.　第1个元素的类型
 * @since 0.1.0
 */
public class Tuple1<T> extends TupleBase {
    private static final long serialVersionUID = 10065918001L;

    /**
     * The 1st element of this tuple.
     */
    public final T _1;

    /**
     * Constructs a {@code Tuple1}.　Tuple1构造器。
     * @param _1 The value of 1st element
     */
    public Tuple1(T _1) {
        this._1 = _1;
    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public Tuple1<T> alias(TupleAlias... aliases) {
        return (Tuple1<T>)super.alias(aliases);
    }

    @Override
    public Tuple1<T> alias(String... aliases) {
        return (Tuple1<T>)super.alias(aliases);
    }

    @Override
    public <R> R element(int n) {
        switch (n) {
            case 0:
                return (R) _1;
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }


}
