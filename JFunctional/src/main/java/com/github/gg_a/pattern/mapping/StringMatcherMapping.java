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

import com.github.gg_a.function.*;
import com.github.gg_a.pattern.PatternIn;
import com.github.gg_a.pattern.matcher.StringRMatcher;
import com.github.gg_a.pattern.matcher.StringVMatcher;
import com.github.gg_a.pattern.type.PatternString;

/**
 * String Matcher Mapping
 * @since 0.7.2
 */
public class StringMatcherMapping extends MatcherMapping<String> {

    private PatternString patternString;

    public StringMatcherMapping(String value, PatternString patternString) {
        this(value);
        this.patternString = patternString;
    }

    public StringMatcherMapping(String value) {
        super(value);
    }

    public StringVMatcher when(String matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, false, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public StringVMatcher whenNext(String matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, false, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R> StringRMatcher<R> when(String matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, false, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R> StringRMatcher<R> whenNext(String matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, false, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }

    public StringVMatcher when(PatternIn<String> matchValues, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, false, patternString);
        return stringVMatcher.when(matchValues, action);
    }

    public StringVMatcher whenNext(PatternIn<String> matchValues, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, false, patternString);
        return stringVMatcher.whenNext(matchValues, action);
    }

    public <R> StringRMatcher<R> when(PatternIn<String> matchValues, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, false, patternString);
        return stringRMatcher.when(matchValues, action);
    }

    public <R> StringRMatcher<R> whenNext(PatternIn<String> matchValues, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, false, patternString);
        return stringRMatcher.whenNext(matchValues, action);
    }
}
