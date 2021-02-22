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
 * A tuple of 7 elements<br>
 * 7个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @param <T3> type of the 3rd element.　第3个元素的类型
 * @param <T4> type of the 4th element.　第4个元素的类型
 * @param <T5> type of the 5th element.　第5个元素的类型
 * @param <T6> type of the 6th element.　第6个元素的类型
 * @param <T7> type of the 7th element.　第7个元素的类型
 * @since 0.1.0
 */
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> extends TupleBase {
    private static final long serialVersionUID = 10065918007L;

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
     * The 4th element of this tuple.
     */
    public final T4 _4;
    /**
     * The 5th element of this tuple.
     */
    public final T5 _5;
    /**
     * The 6th element of this tuple.
     */
    public final T6 _6;
    /**
     * The 7th element of this tuple.
     */
    public final T7 _7;

    /**
     * Constructs a {@code Tuple7}.　Tuple7构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     * @param _3 The value of 3rd element
     * @param _4 The value of 4th element
     * @param _5 The value of 5th element
     * @param _6 The value of 6th element
     * @param _7 The value of 7th element
     */
    public Tuple7(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7){
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
    }

    @Override
    public int arity() {
        return 7;
    }

    @Override
    public Tuple7<T1, T2, T3, T4, T5, T6, T7> alias(TupleAlias... aliases) {
        return (Tuple7<T1, T2, T3, T4, T5, T6, T7>)super.alias(aliases);
    }

    @Override
    public Tuple7<T1, T2, T3, T4, T5, T6, T7> alias(String... aliases) {
        return (Tuple7<T1, T2, T3, T4, T5, T6, T7>)super.alias(aliases);
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
            case 3:
                return (R) _4;
            case 4:
                return (R) _5;
            case 5:
                return (R) _6;
            case 6:
                return (R) _7;
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }

}
