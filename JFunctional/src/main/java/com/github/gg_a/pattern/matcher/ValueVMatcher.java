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
import com.github.gg_a.pattern.PatternIn;
import java.util.Objects;

/**
 * Value Matcher with void
 *
 * @since 0.7.0
 */
public class ValueVMatcher<V> extends SimpleVMatcher<V, V, V> {

    public ValueVMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public ValueVMatcher(V value) {
        super(value);
    }

    @Override
    public ValueVMatcher<V> when(V value, V1<V> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    action.$(this.value);
                }
            } else if (value.equals(this.value)) {
                isMatch = true;
                action.$(this.value);
            }

        }
        return this;
    }

    @Override
    public ValueVMatcher<V> whenNext(V value, V1<V> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (value == null || this.value == null) {
                if (this.value == value)
                    action.$(this.value);
            } else if (value.equals(this.value)) action.$(this.value);
        }

        return this;
    }

    public ValueVMatcher<V> when(PatternIn<V> values, V1<V> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$(this.value);
                }
                return this;
            }

            if (values != null && values.getVs().contains(this.value)) {
                isMatch = true;
                action.$(this.value);
            }
        }
        return this;
    }

    public ValueVMatcher<V> whenNext(PatternIn<V> values, V1<V> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    action.$(this.value);
                }
                return this;
            }
            if (values != null && values.getVs().contains(this.value)) action.$(this.value);
        }
        return this;
    }

    @Override
    public Void orElse(V1<V> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            action.$(this.value);
        }
        return returnValue;
    }

}
