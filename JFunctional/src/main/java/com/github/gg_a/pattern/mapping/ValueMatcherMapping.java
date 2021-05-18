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
import com.github.gg_a.pattern.matcher.BooleanRMatcher;
import com.github.gg_a.pattern.matcher.BooleanVMatcher;
import com.github.gg_a.pattern.matcher.ValueRMatcher;
import com.github.gg_a.pattern.matcher.ValueVMatcher;

/**
 * Value Matcher Mapping
 * @since 0.7.0
 */
public class ValueMatcherMapping<V> extends MatcherMapping<V> {

    public ValueMatcherMapping(V value) {
        super(value);
    }

    public BooleanVMatcher<V> when(Boolean matchValue, V1<V> action) {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.when(matchValue, action);
    }
    public BooleanVMatcher<V> whenNext(Boolean matchValue, V1<V> action) {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.whenNext(matchValue, action);
    }

    public <R> BooleanRMatcher<V, R> when(Boolean matchValue, R1<V ,R> action) {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.when(matchValue, action);
    }

    public <R> BooleanRMatcher<V, R> whenNext(Boolean matchValue, R1<V ,R> action) {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.whenNext(matchValue, action);
    }

    public ValueVMatcher<V> when(V matchValue, V1<V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.when(matchValue, action);
    }

    public ValueVMatcher<V> whenNext(V matchValue, V1<V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.whenNext(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> when(V matchValue, R1<V ,R> action) {
        ValueRMatcher<V, R> valueVMatcher = new ValueRMatcher<>(value);
        return valueVMatcher.when(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> whenNext(V matchValue, R1<V ,R> action) {
        ValueRMatcher<V, R> valueVMatcher = new ValueRMatcher<>(value);
        return valueVMatcher.whenNext(matchValue, action);
    }
    
    public ValueVMatcher<V> when(PatternIn<V> matchValues, V1<V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.when(matchValues, action);
    }

    public ValueVMatcher<V> whenNext(PatternIn<V> matchValues, V1<V> action) {
        ValueVMatcher<V>valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.whenNext(matchValues, action);
    }

    public <R> ValueRMatcher<V, R> when(PatternIn<V> matchValues, R1<V ,R> action) {
        ValueRMatcher<V, R> valueVMatcher = new ValueRMatcher<>(value);
        return valueVMatcher.when(matchValues, action);
    }

    public <R> ValueRMatcher<V, R> whenNext(PatternIn<V> matchValues, R1<V ,R> action) {
        ValueRMatcher<V, R> valueVMatcher = new ValueRMatcher<>(value);
        return valueVMatcher.whenNext(matchValues, action);
    }
}
