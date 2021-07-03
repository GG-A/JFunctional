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
 * Type Matcher with void
 *
 * @since 0.7.0
 */
public class TypeVMatcher<V> implements Matcher {

    protected Void returnValue;
    protected V value;
    protected boolean isMatch;

    public TypeVMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public TypeVMatcher(V value) {
        this(value, false);
    }

    public <C> TypeVMatcher<V> when(Class<C> value, V1<C> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null) {
                    isMatch = true;
                    action.$((C) this.value);
                }
            } else if (this.value.getClass() == value) {
                isMatch = true;
                action.$((C) this.value);
            }
        }
        return this;
    }

    public <C> TypeVMatcher<V> whenNext(Class<C> value, V1<C> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null)
                    action.$((C) this.value);
            } else if (this.value.getClass() == value) action.$((C) this.value);
        }
        return this;
    }

    public Void orElse(V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }
}
