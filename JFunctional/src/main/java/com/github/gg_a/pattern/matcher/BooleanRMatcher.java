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

/**
 * Boolean Matcher with Return value
 * @since 0.7.0
 */
public class BooleanRMatcher<V, R> extends SimpleRMatcher<V, Boolean, V, R> {

    public BooleanRMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public BooleanRMatcher(V value) {
        super(value);
    }

    @Override
    public BooleanRMatcher<V, R> when(Boolean value, R1<V, R> action) {
        if (!isMatch) {
            super.when(value, action);
            if (value) {
                isMatch = true;
                returnValue = action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public BooleanRMatcher<V, R> whenNext(Boolean value, R1<V, R> action) {
        if (!isMatch) {
            super.whenNext(value, action);
            if (value) returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public R orElse(R1<V, R> action) {
        if (!isMatch) {
            super.orElse(action);
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
