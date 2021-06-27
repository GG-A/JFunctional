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
package com.github.gg_a.text;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.github.gg_a.tuple.Tuple;
import com.github.gg_a.tuple.Tuple3;

/**
 * String Interpolator. <br>
 * 字符串插值器
 * @since 0.8.0
 */
public class SI {

    private final static Map<String, List<StringToken>> templateCache=new ConcurrentHashMap<>();

    private final static int cacheSize = 1000;

    private final Map<String, Object> valueMap = new HashMap<>();

    public SI() {

    }

    public SI(final Tuple... tuples) {
        tuplesPutToMap(tuples);
    }

    public SI(final Map<String, Object> valueMap) {
        this.valueMap.putAll(valueMap);
    }

    public static SI of(final Tuple... tuples) {
        return new SI(tuples);
    }

    public static SI of(final Map<String, Object> map) {
        return new SI(map);
    }

    public SI add(Tuple... tuples) {
        tuplesPutToMap(tuples);
        return this;
    }

    public SI add(Map<String, Object> valueMap) {
        if (valueMap != null) {
            this.valueMap.putAll(valueMap);
        }

        return this;
    }

    public SI set(Tuple... tuples) {
        valueMap.clear();
        tuplesPutToMap(tuples);
        return this;
    }

    public SI set(Map<String, Object> valueMap) {
        this.valueMap.clear();
        if (valueMap != null)  this.valueMap.putAll(valueMap);
        return this;
    }

    public SI del(String... keys) {
        if (keys != null) {
            Arrays.stream(keys).forEach(valueMap::remove);
        }
        return this;
    }

    private void tuplesPutToMap(Tuple... tuples) {
        if (tuples != null) {
            Arrays.stream(tuples)
                    .filter(e -> e != null && e.arity() != 0)
                    .forEach(t -> valueMap.putAll(t.toMap()));
        }
    }

    /**
     * Interpolating for strings.<br>
     * 执行插值程序，解析字符串
     * @param source source string
     * @return string that has been processed
     * @since 0.8.0
     */
    public String $(String source) {
        if (source == null) return null;

        List<StringToken> tokens = getTokens(source);
        StringBuilder parsed = new StringBuilder();
        tokens.forEach(token -> {
            String value = token.getValue();
            if (token.getType() == StringType.STRING) {
                parsed.append(value);
            }else {
                if (valueMap.containsKey(value)) {
                    parsed.append(valueMap.get(value));
                }else {
                    parsed.append(token.getOriginValue());
                }
            }
        });

        return parsed.toString();
    }

    private List<StringToken> getTokens(String source) {
        List<StringToken> tokens;
        if (templateCache.containsKey(source)) {
            tokens = templateCache.get(source);
        }else {
            tokens = StringExtractor.split(source);
            if (templateCache.size() < cacheSize) templateCache.put(source, tokens);
        }
        return tokens;
    }

    /**
     * Interpolating for strings.<br>
     * 执行插值程序，解析字符串
     * @param source source string
     * @return string that has been processed
     * @since 0.8.0
     */
    public String $(StringBuffer source) {
        return $(source.toString());
    }

    public Map<String, Object> getValueMap() {
        return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
