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
 * Base tuple
 */
public abstract class TupleBase implements Tuple {

    private static final long serialVersionUID = 10065917090L;

    /**
     * List of aliases.　别名列表。
     */
    private List<String> aliasList = new ArrayList<>();
    /**
     * Map of aliases.　别名与序号键值对
     */
    private Map<String, Integer> alias_index = new HashMap<>();

    @Override
    public Tuple alias(String... aliases) {
        if (arity() == 0) throw new UnsupportedOperationException("`alias` method is unsupported in Tuple0. Because Tuple0 is empty tuple. Tuple0不支持调用alias方法，因为Tuple0是一个空元组。");

        if (aliases == null) {
            if (arity() == 1) {
                alias_index.clear();
                aliasList.clear();
                putToMap(null, 0);
            }else {
                throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
            }
        }else {
            if (arity() != aliases.length)  throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
            alias_index.clear();
            aliasList.clear();
            for (int i = 0; i < aliases.length; i++) putToMap(aliases[i], i);
        }

        return this;
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>(aliasList);
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
    public boolean containsAlias(String alias) {
        return arity() != 0 && aliasList.contains(alias);
    }

    @Override
    public <R> Tuple2<String, R> elementWithAlias(int n) {
        if (n >= arity()) throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());

        if (aliasList.isEmpty()) {
            throw new AliasNotSetException("The aliases not set. Please call `aliases` method first. 别名未设置，请先调用aliases方法设置别名。");
        }
        String alias = aliasList.get(n);
        R element = this.<R>element(n);

        return new Tuple2<>(alias, element);
    }

    @Override
    public String toString() {
        if (arity() == 0) {
            return "()";
        }else {
            List<String> strList = new ArrayList<>();
            for (int i = 0; i < arity(); i++) {
                strList.add(_nStr(element(i)));
            }
            return alias_index.isEmpty() ? "(" + String.join(", ", strList) + ")" : "(" + concatElement(strList) + ")";
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

    private String concatElement(List<String> strList) {
        ArrayList<String> tempStrList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            String alias = aliasList.get(i);
            alias = (alias == null ? "`null`" : alias);
            tempStrList.add(alias + ": " + strList.get(i));
        }
        return String.join(", ", tempStrList);
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
