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

import com.github.gg_a.function.V1;

import java.util.Objects;

/**
 * Boolean Matcher with void
 *
 * @since 0.7.0
 */
public class BooleanVMatcher<V> extends SimpleVMatcher<V, Boolean, V> {

    public BooleanVMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public BooleanVMatcher(V value) {
        this(value, false);
    }

    @Override
    public BooleanVMatcher<V> when(Boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null) {
                    isMatch = true;
                    action.$(this.value);
                }
            } else if (value) {
                isMatch = true;
                action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public BooleanVMatcher<V> whenNext(Boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null)
                    action.$(this.value);
            } else if (value) action.$(this.value);
        }
        return this;
    }

    @Override
    public Void orElse(V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }
}
