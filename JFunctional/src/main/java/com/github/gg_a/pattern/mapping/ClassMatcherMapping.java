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
package com.github.gg_a.pattern.mapping;

import com.github.gg_a.function.R1;
import com.github.gg_a.function.V1;
import com.github.gg_a.pattern.PatternIn;
import com.github.gg_a.pattern.matcher.ValueRMatcher;
import com.github.gg_a.pattern.matcher.ValueVMatcher;

/**
 * Class Matcher Mapping
 * @since 0.7.0
 */
public class ClassMatcherMapping<V> extends MatcherMapping<V> {

    public ClassMatcherMapping(V value) {
        super(value);
    }

    public ValueVMatcher<V> when(V matchValue, V1<V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        valueVMatcher.when(matchValue, action);
        return valueVMatcher;
    }

    public ValueVMatcher<V> whenNext(V matchValue, V1<V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        valueVMatcher.whenNext(matchValue, action);
        return valueVMatcher;
    }

    public <R> ValueRMatcher<V, R> when(V matchValue, R1<V, R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        valueRMatcher.when(matchValue, action);
        return valueRMatcher;
    }

    public <R> ValueRMatcher<V, R> whenNext(V matchValue, R1<V, R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        valueRMatcher.whenNext(matchValue, action);
        return valueRMatcher;
    }

    public <R> ValueRMatcher<V, R> when(PatternIn<V> matchValues, R1<V, R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        valueRMatcher.when(matchValues, action);
        return valueRMatcher;
    }

    public <R> ValueRMatcher<V, R> whenNext(PatternIn<V> matchValues, R1<V, R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        valueRMatcher.whenNext(matchValues, action);
        return valueRMatcher;
    }
}
