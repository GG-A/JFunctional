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
package com.github.gg_a.function;

/**
 * Represents a function that accepts 2 arguments and produces a result, and will throw exception<br>
 * 表示一个接收 2个参数 并返回结果且抛出异常的函数
 *
 * @param <T1> type of the 1st param.　第1个参数类型
 * @param <T2> type of the 2nd param.　第2个参数类型
 * @param <R> return type  返回值类型
 * @param <E> Throwable or subclass of Throwable.　Throwable类及其子类
 * @since 0.0.4
 */
@FunctionalInterface
public interface RT2<T1, T2, R, E extends Throwable> extends LambdaRT {
    R $(T1 t1, T2 t2) throws E;

    default int arity(){
        return 2;
    }
}
