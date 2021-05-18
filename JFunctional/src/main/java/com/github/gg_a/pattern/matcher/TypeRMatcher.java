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

import com.github.gg_a.function.R1;
import java.util.Objects;

/**
 * Type Matcher with return value
 * @since 0.7.0
 */
public class TypeRMatcher<V, R> implements Matcher{

    protected R returnValue;
    protected V value;
    protected boolean isMatch;

    public TypeRMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public TypeRMatcher(V value) {
        this(value, false);
    }

    public <C> TypeRMatcher<V, R> when(Class<C> value, R1<C, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(value);
            Objects.requireNonNull(action);
            if (this.value.getClass() == value) {
                isMatch = true;
                returnValue = action.$((C) this.value);
            }
        }
        return this;
    }

    public <C> TypeRMatcher<V, R> whenNext(Class<C> value, R1<C, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(value);
            Objects.requireNonNull(action);
            if (this.value.getClass() == value) returnValue = action.$((C) this.value);
        }
        return this;
    }

    public R orElse(R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
