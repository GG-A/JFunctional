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
 * A tuple of 1 element<br>
 * 1个元素的元组
 *
 * @param <T> type of the 1st element.　第1个元素的类型
 */
public class Tuple1<T> implements Tuple, Serializable {
    private static final long serialVersionUID = 10065918001L;

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
    public Tuple1<T> alias(String... aliases) {
        if (aliases == null) {
            alias_index.clear();
            aliasList.clear();

            putToMap(null, 0);

            return this;
        }else {
            if (aliases.length != arity()) {
                throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
            }else {
                alias_index.clear();
                aliasList.clear();

                putToMap(aliases[0], 0);

                return this;
            }
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
        } else {
            throw new AliasNotFoundException("the alias `" + alias + "` not found. " + " 别名`" + alias + "`没有找到。");
        }
    }

    @Override
    public <R> R element(int n) {
        switch (n) {
            case 0:
                return (R) _1;
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
        String _1str = this.<T>_nStr(_1);

        if (aliasList.isEmpty()) {
            return "(" + _1str + ")";
        } else {
            String alias = aliasList.get(0);
            alias = (alias == null ? "`null`" : alias);
            return "(" + alias + ": " + _1str + ")";
        }
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
