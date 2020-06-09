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
 * A tuple of 2 elements<br>
 * 2个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 */
public class Tuple2<T1, T2> implements Tuple, Serializable {
    private static final long serialVersionUID = 10065918002L;

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
     * Constructs a {@code Tuple2}.　Tuple2构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     */
    public Tuple2(T1 _1, T2 _2){
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public Tuple2<T1, T2> alias(String... aliases) {
        if (aliases.length != arity()) {
            throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
        }else {
            alias_index.clear();
            aliasList.clear();

            putToMap(aliases[0], 0);
            putToMap(aliases[1], 1);

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
        String[] strs = {_nStr(_1), _nStr(_2)};

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
