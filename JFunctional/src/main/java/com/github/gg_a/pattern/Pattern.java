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
package com.github.gg_a.pattern;

import com.github.gg_a.pattern.mapping.BooleanMatcherMapping;
import com.github.gg_a.pattern.mapping.ClassMatcherMapping;
import com.github.gg_a.pattern.mapping.TypeMatcherMapping;
import com.github.gg_a.pattern.mapping.ValueMatcherMapping;
import com.github.gg_a.pattern.type.PatternBoolean;
import com.github.gg_a.pattern.type.PatternDefault;
import com.github.gg_a.pattern.type.PatternType;
import com.github.gg_a.pattern.type.PatternValue;
import java.util.Objects;

/**
 * Pattern Matching for Java
 * @since 0.7.0
 */
public class Pattern {
    public static PatternDefault DEFAULT = PatternDefault.DEFAULT;
    public static PatternValue VALUE = PatternValue.VALUE;
    public static PatternBoolean BOOLEAN = PatternBoolean.BOOLEAN;
    public static PatternType TYPE = PatternType.TYPE;

    public static ClassMatcherMapping<Class<?>> match(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return new ClassMatcherMapping<>(clazz);
    }

    public static <V> ValueMatcherMapping<V> match(V value) {
        return match(value, DEFAULT);
    }

    public static <V> ValueMatcherMapping<V> match(V value, PatternDefault patternDefault) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(patternDefault);
        return new ValueMatcherMapping<V>(value);
    }

    public static <V> ValueMatcherMapping<V> match(V value, PatternValue patternValue) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(patternValue);
        return new ValueMatcherMapping<V>(value);
    }

    public static <V> BooleanMatcherMapping<V> match(V value, PatternBoolean patternBoolean) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(patternBoolean);
        return new BooleanMatcherMapping<>(value);
    }

    public static <V> TypeMatcherMapping<V> match(V value, PatternType patternType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(patternType);
        return new TypeMatcherMapping<>(value);
    }

    public static <T> PatternIn<T> in(T... values) {
        return PatternIn.in(values);
    }

}
