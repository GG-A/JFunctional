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
 * A tuple of 7 elements<BR/>
 * 7个元素的元组
 */
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> implements Tuple {

    private Map<String, Integer> alias_index = new HashMap<>();
    private Map<Integer, String> index_alias = new HashMap<>();

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
     * @param alias4 别名4
     * @param alias5 别名5
     * @param alias6 别名6
     * @param alias7 别名7
     * @return return `this`. 返回自身对象
     */
    public Tuple7<T1, T2, T3, T4, T5, T6, T7> alias(String alias1, String alias2, String alias3, String alias4,
                                                    String alias5, String alias6, String alias7){
        alias_index.clear();
        index_alias.clear();

        putToMap(alias1, 0);
        putToMap(alias2, 1);
        putToMap(alias3, 2);
        putToMap(alias4, 3);
        putToMap(alias5, 4);
        putToMap(alias6, 5);
        putToMap(alias7, 6);

        return this;
    }

    private void putToMap(String alias, int index) {
        if (alias_index.containsKey(alias)) {
            throw new AliasDuplicateException("the alias `" + alias + "` is existed. " + "别名 `" + alias + "` 已经存在。 ");
        }else {
            alias_index.put(alias, index);
            index_alias.put(index, alias);
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
            case 6:
                return (R) _7;
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
        String _2str = _2 == null ? "null" : _2.toString();
        String _3str = _3 == null ? "null" : _3.toString();
        String _4str = _4 == null ? "null" : _4.toString();
        String _5str = _5 == null ? "null" : _5.toString();
        String _6str = _6 == null ? "null" : _6.toString();
        String _7str = _7 == null ? "null" : _7.toString();
        _1str = this.<T1>_nStr(_1str, _1);
        _2str = this.<T2>_nStr(_2str, _2);
        _3str = this.<T3>_nStr(_3str, _3);
        _4str = this.<T4>_nStr(_4str, _4);
        _5str = this.<T5>_nStr(_5str, _5);
        _6str = this.<T6>_nStr(_6str, _6);
        _7str = this.<T7>_nStr(_7str, _7);


        if (alias_index.isEmpty()) {
            return "(" + _1str + ", " + _2str + ", " + _3str + ", " + _4str + ", " + _5str + ", " + _6str + ", " + _7str + ")";
        }else {
            return "(" + index_alias.get(0) + ": " + _1str + ", "
                    + index_alias.get(1) + ": " + _2str + ", "
                    + index_alias.get(2) + ": " + _3str + ", "
                    + index_alias.get(3) + ": " + _4str + ", "
                    + index_alias.get(4) + ": " + _5str + ", "
                    + index_alias.get(5) + ": " + _6str + ", "
                    + index_alias.get(6) + ": " + _7str
                    + ")";
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
