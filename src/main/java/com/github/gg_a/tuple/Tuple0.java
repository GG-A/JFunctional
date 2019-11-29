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

import java.io.Serializable;

/**
 * A tuple of no element<br>
 * 一个没有元素的元组
 */
public class Tuple0 implements Tuple, Serializable {
    private static final long serialVersionUID = 10065918000L;

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public <R> R __(String alias){
        throw new AliasNotFoundException("the alias `" + alias + "` not found. "+" 别名`" + alias + "`没有找到。");
    }

    @Override
    public <R> R element(int n) {
        throw new IndexOutOfBoundsException("Index out of range: " + n);
    }

    @Override
    public <R> Tuple2<String, R> elementWithAlias(int n) {
        throw new IndexOutOfBoundsException("Index out of range: " + n);
    }


    @Override
    public String toString() {
        return "()";
    }
}
