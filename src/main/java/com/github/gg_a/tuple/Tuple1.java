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

import java.util.HashMap;
import java.util.Map;

/**
 * A tuple of 1 element<BR/>
 * 1个元素的元组
 */
public class Tuple1<T> implements Tuple {

    private Map<String, Integer> alias_index = new HashMap<>();
    private Map<Integer, String> index_alias = new HashMap<>();

    /**
     * The 1st element of this tuple.
     */
    public final T _1;

    public Tuple1(T _1) {
        this._1 = _1;
    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public <R> R __(String alias) {
        if (alias_index.containsKey(alias)) {
            return element(alias_index.get(alias));
        } else {
            throw new AliasNotFoundException("the alias `" + alias + "` not found. " + " 别名`" + alias + "`没有找到。");
        }
    }

    /**
     * set alias for tuple's elements. <BR/>
     * 为元组的每个元素设置别名
     *
     * @param alias 别名
     * @return return `this`. 返回自身对象
     */
    public Tuple1<T> alias(String alias) {
        alias_index.clear();
        index_alias.clear();

        alias_index.put(alias, 0);
        index_alias.put(0, alias);

        return this;
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
        String alias = index_alias.get(n);
        R element = this.<R>element(n);

        return new Tuple2<String, R>(alias, element);
    }

    @Override
    public String toString() {
        String _1str = _1 == null ? "null" : _1.toString();
        _1str = this.<T>_nStr(_1str, _1);

        if (alias_index.isEmpty()) {
            return "(" + _1str + ")";
        } else {
            return "(" + index_alias.get(0) + ": " + _1str + ")";
        }
    }

    private <R> String _nStr(String nstr, R _n) {
        // 如果 _n == null，那么无论 R 是什么类型，`_n instanceof Object` 都为 false
        if (_n instanceof String){
            nstr = "\"" + nstr + "\"";
        }
        return nstr;
    }


}
