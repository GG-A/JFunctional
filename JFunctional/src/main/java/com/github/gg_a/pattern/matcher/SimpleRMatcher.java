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
package com.github.gg_a.pattern.matcher;

import com.github.gg_a.function.*;

/**
 * Simple Matcher with return value
 * @since 0.7.0
 */
public abstract class SimpleRMatcher<V, P, L, R> extends SimpleMatcher<V, P, L, R> {
    protected R returnValue;
    protected V value;
    protected boolean isMatch;

    public SimpleRMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public SimpleRMatcher(V value) {
        this(value, false);
    }

    public abstract SimpleRMatcher<V, P, L, R> when(P value, R1<L, R> action);

    public abstract SimpleRMatcher<V, P, L, R> whenNext(P value, R1<L, R> action);

    public abstract R orElse(R1<V, R> action);
}
