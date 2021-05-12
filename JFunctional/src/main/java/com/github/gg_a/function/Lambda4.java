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
 * A Lambda that accepts 4 arguments
 * @since 0.6.1
 */
public interface Lambda4<T1, T2, T3, T4> extends Lambda{
    /**
     * The number of the Lambda params. <br>
     * Lambda表达式参数的数量
     * @return The number of the Lambda params
     */
    default int arity(){
        return 4;
    }
}
