/*
 * Copyright 2019 GG-A, <2018158885@qq.com>
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
package com.github.gg_a.function;

/**
 * Represents a function that accepts 2 arguments and returns no result, and will throw exception
 * 表示一个接收 2个参数 不返回任何结果且抛出异常的函数
 *
 * @param <T1> first param type  第1个参数类型
 * @param <T2> second param type  第2个参数类型
 * @param <E> Exception or subclass of Exception  Exception类及其子类
 */
@FunctionalInterface
public interface VT2<T1, T2, E extends Exception> {
    void $(T1 t1, T2 t2) throws E;
}