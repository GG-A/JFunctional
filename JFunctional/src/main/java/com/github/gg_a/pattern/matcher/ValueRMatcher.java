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
import com.github.gg_a.pattern.PatternIn;

import java.util.Objects;

/**
 * Value Matcher with return value
 *
 * @since 0.7.0
 */
public class ValueRMatcher<V, R> extends SimpleRMatcher<V, V, V, R> {

    public ValueRMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public ValueRMatcher(V value) {
        super(value);
    }

    @Override
    public ValueRMatcher<V, R> when(V value, R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            } else if (value.equals(this.value)) {
                isMatch = true;
                returnValue = action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> whenNext(V value, R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (value == null || this.value == null) {
                if (this.value == value)
                    returnValue = action.$(this.value);
            } else if (value.equals(this.value)) returnValue = action.$(this.value);
        }
        return this;
    }

    public ValueRMatcher<V, R> when(PatternIn<V> values, R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }else{
                    if (values.getVs().contains(this.value)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                }
            } else if (values != null && values.getVs().contains(this.value)) {
                isMatch = true;
                returnValue = action.$(this.value);
            }
        }
        return this;
    }

    public ValueRMatcher<V, R> whenNext(PatternIn<V> values, R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null) {
                    returnValue = action.$(this.value);
                }else{
                    if (values.getVs().contains(this.value)) {
                        returnValue = action.$(this.value);
                    }
                }
            } else if (values != null && values.getVs().contains(this.value)) returnValue = action.$(this.value);

        }

        return this;
    }

    @Override
    public R orElse(R1<V, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            returnValue = action.$(this.value);
        }
        return returnValue;
    }

}
