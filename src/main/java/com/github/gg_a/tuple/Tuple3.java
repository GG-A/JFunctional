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
 * A tuple of 3 elements<BR/>
 * 3个元素的元组
 */
public class Tuple3<T1, T2, T3> implements Tuple {

    private Map<String, Integer> alias_index = new HashMap<>();
    private Map<Integer, String> Index_alias = new HashMap<>();

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
    public <R> R __(String alias) {
        if (alias_index.containsKey(alias)) {
            return element(alias_index.get(alias));
        }else {
            throw new AliasNotFoundException("the alias `" + alias + "` not found. "+" 别名`" + alias + "`没有找到。");
        }
    }

    /**
     * set alias for tuple's elements. <BR/>
     * 为元组的每个元素设置别名
     * @param alias1 别名1
     * @param alias2 别名2
     * @param alias3 别名3
     * @return return `this`. 返回自身对象
     */
    public Tuple3<T1, T2, T3> alias(String alias1, String alias2, String alias3){
        alias_index.clear();
        Index_alias.clear();

        putToMap(alias1, 0);
        putToMap(alias2, 1);
        putToMap(alias3, 2);

        return this;
    }

    private void putToMap(String alias, int index) {
        if (alias_index.containsKey(alias)) {
            throw new AliasDuplicateException("the alias `" + alias + "` is existed. " + "别名 `" + alias + "` 已经存在。 ");
        }else {
            alias_index.put(alias, index);
            Index_alias.put(index, alias);
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
            default:
                throw new IndexOutOfBoundsException(n);
        }
    }

    @Override
    public <R> Tuple2<String, R> elementWithAlias(int n) {
        String alias = Index_alias.get(n);
        R element = this.<R>element(n);

        return new Tuple2<String, R>(alias, element);
    }

    @Override
    public String toString() {
        String _1str = _1 == null ? "null" : _1.toString();
        String _2str = _2 == null ? "null" : _2.toString();
        String _3str = _3 == null ? "null" : _3.toString();
        _1str = this.<T1>_nStr(_1str, _1);
        _2str = this.<T2>_nStr(_2str, _2);
        _3str = this.<T3>_nStr(_3str, _3);


        if (alias_index.isEmpty()) {
            return "(" + _1str + ", " + _2str + ", " + _3str + ")";
        }else {
            return "(" + Index_alias.get(0) + ": " + _1str + ", "
                    + Index_alias.get(1) + ": " + _2str + ", "
                    + Index_alias.get(2) + ": " + _3str
                    + ")";
        }
    }

    private <R> String _nStr(String nstr, R _n) {
        // 如果 _n == null，那么无论 R 是什么类型，_n instanceof Object 都为 false
        if (_n instanceof String){
            nstr = "\"" + nstr + "\"";
        }
        return nstr;
    }


}
