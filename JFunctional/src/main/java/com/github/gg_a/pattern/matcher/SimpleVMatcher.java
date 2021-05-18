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
import java.util.Objects;

/**
 * Simple Matcher with void
 * @since 0.7.0
 */
public abstract class SimpleVMatcher<V, P, L> extends SimpleMatcher<V, P, L, Void> {
    protected Void returnValue;
    protected V value;
    protected boolean isMatch;

    public SimpleVMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public SimpleVMatcher(V value) {
        this(value, false);
    }

    public SimpleVMatcher<V, P, L> when(P value, V1<L> action){
        Objects.requireNonNull(value);
        Objects.requireNonNull(action);
        return this;
    }

    public SimpleVMatcher<V, P, L> whenNext(P value, V1<L> action){
        Objects.requireNonNull(value);
        Objects.requireNonNull(action);
        return this;
    }

    public Void orElse(V1<V> action){
        Objects.requireNonNull(action);
        return returnValue;
    }

}
