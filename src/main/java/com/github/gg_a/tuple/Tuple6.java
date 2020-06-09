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

import com.github.gg_a.exception.AliasDuplicateException;
import com.github.gg_a.exception.AliasNotFoundException;
import com.github.gg_a.exception.AliasNotSetException;
import com.github.gg_a.exception.NumberOfAliasesException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A tuple of 6 elements<br>
 * 6个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @param <T3> type of the 3rd element.　第3个元素的类型
 * @param <T4> type of the 4th element.　第4个元素的类型
 * @param <T5> type of the 5th element.　第5个元素的类型
 * @param <T6> type of the 6th element.　第6个元素的类型
 */
public class Tuple6<T1, T2, T3, T4, T5, T6> implements Tuple, Serializable {
    private static final long serialVersionUID = 10065918006L;

    /**
     * List of aliases.　别名列表。
     */
    private List<String> aliasList = new ArrayList<>();

    /**
     * Map of aliases.　别名与序号键值对
     */
    private Map<String, Integer> alias_index = new HashMap<>();

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
     * Constructs a {@code Tuple6}.　Tuple6构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     * @param _3 The value of 3rd element
     * @param _4 The value of 4th element
     * @param _5 The value of 5th element
     * @param _6 The value of 6th element
     */
    public Tuple6(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6){
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
    }

    @Override
    public int arity() {
        return 6;
    }

    @Override
    public Tuple6<T1, T2, T3, T4, T5, T6> alias(String... aliases) {
        if (aliases.length != arity()) {
            throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
        }else {
            alias_index.clear();
            aliasList.clear();

            for (int i = 0; i < aliases.length; i++) putToMap(aliases[i], i);

            return this;
        }
    }

    private void putToMap(String alias, int index) {
        if (alias_index.containsKey(alias)) {
            throw new AliasDuplicateException("the alias `" + alias + "` is existed. " + "别名 `" + alias + "` 已经存在。 ");
        }else {
            alias_index.put(alias, index);
            aliasList.add(alias);
        }
    }

    @Override
    public <R> R __(String alias) {
        if (alias_index.containsKey(alias)) {
            return element(alias_index.get(alias));
        }else {
            throw new AliasNotFoundException("the alias `" + alias + "` not found. "+" 别名`" + alias + "`没有找到。");
        }
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
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n);
        }
    }

    @Override
    public <R> Tuple2<String, R> elementWithAlias(int n) {
        if (aliasList.isEmpty()) {
            throw new AliasNotSetException("The aliases not set. Please call `aliases` method first. 别名未设置，请先调用aliases方法设置别名。");
        }
        String alias = aliasList.get(n);
        R element = this.<R>element(n);

        return new Tuple2<String, R>(alias, element);
    }

    @Override
    public String toString() {
        String[] strs = {_nStr(_1), _nStr(_2), _nStr(_3), _nStr(_4), _nStr(_5), _nStr(_6)};

        if (alias_index.isEmpty()) {
            return "(" + String.join(", ", strs) + ")";
        }else {
            return "(" + concatElement(strs) + ")";
        }
    }

    private String concatElement(String[] strArr) {
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < strArr.length; i++) {
            String alias = aliasList.get(i);
            alias = (alias == null ? "`null`" : alias);
            strList.add(alias + ": " + strArr[i]);
        }
        return String.join(", ", strList);
    }

    private <R> String _nStr(R _n) {
        String nstr = _n == null ? "null" : _n.toString();
        // 如果 _n == null，那么无论 R 是什么类型，`_n instanceof Object` 都为 false
        if (_n instanceof String){
            nstr = "\"" + nstr + "\"";
        }
        return nstr;
    }

}
