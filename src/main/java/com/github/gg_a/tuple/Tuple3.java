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
 * A tuple of 3 elements<br>
 * 3个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @param <T3> type of the 3rd element.　第3个元素的类型
 * @since 0.1.0
 */
public class Tuple3<T1, T2, T3> extends TupleBase {
    private static final long serialVersionUID = 10065918003L;

    /**
     * The 1st element of this tuple.
     */
    public final T1 _1;
    /**
     * The 2nd element of this tuple.
     */
    public final T2 _2;
    /**
     * The 3rd element of this tuple.
     */
    public final T3 _3;

    /**
     * Constructs a {@code Tuple3}.　Tuple3构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     * @param _3 The value of 3rd element
     */
    public Tuple3(T1 _1, T2 _2, T3 _3){
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    @Override
    public int arity() {
        return 3;
    }

    @Override
    public Tuple3<T1, T2, T3> alias(TupleAlias... aliases) {
        return (Tuple3<T1, T2, T3>)super.alias(aliases);
    }

    @Override
    public Tuple3<T1, T2, T3> alias(String... aliases) {
        return (Tuple3<T1, T2, T3>)super.alias(aliases);
    }

    @Override
    public <R> R element(int n) {
        switch (n) {
            case 0:
                return (R) _1;
            case 1:
                return (R) _2;
            case 2:
                return (R) _3;
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }

}
