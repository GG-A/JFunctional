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

import com.github.gg_a.exception.UnexpectedParameterException;
import com.github.gg_a.tuple.Tuple;

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

    /**
     * Instantiate an SI object by key-value pairs.
     * @param kvs key-value pairs
     * @return SI object
     * @since 0.8.2
     */
    public static SI of(Object... kvs) {
        Map<String, Object> kvMap = toMap(false, false, kvs);
        return of(kvMap);
    }

    /**
     * Instantiate an SI object by key-value pairs, and key must be end with " -&gt;",
     * and key will be removed leading and trailing whitespace. <br>
     * <b>Examples:</b>
     * <pre>
     * String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";
     *
     * SI si = SI.fill("         ip -&gt;", "127.0.0.1",
     *                 "         db -&gt;", "testdb",
     *                 "       port -&gt;", 3306,
     *                 "     dbType -&gt;", "mysql",
     *                 " other_info -&gt;", Tuple.of("isCluster", true),
     *                 "description -&gt;", new Object());
     *
     * String dbInfo = si.$(infoTemplate);
     * </pre>
     * @param kvs key-value pairs
     * @return SI object
     * @since 0.8.2
     */
    public static SI fill(Object... kvs) {
        Map<String, Object> kvMap = toMap(true, true, kvs);
        return of(kvMap);
    }

    /**
     * Instantiate an SI object by key-value pairs, and key must be end with " -&gt;". <br>
     * <b>Examples:</b>
     * <pre>
     * SI si = SI.load("ip -&gt;", "127.0.0.1",
     *                 "port -&gt;", 3306,
     *                 "db -&gt;", "testdb",
     *                 "dbType -&gt;", "mysql",
     *                 "other_info -&gt;", Tuple.of("isCluster", true),
     *                 "description -&gt;", new Object());
     *
     *  String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
     * </pre>
     * @param kvs key-value pairs
     * @return SI object
     * @since 0.8.2
     */
    public static SI load(Object... kvs) {
        Map<String, Object> kvMap = toMap(true, false, kvs);
        return of(kvMap);
    }

    public SI add(Tuple... tuples) {
        tuplesPutToMap(tuples);
        return this;
    }

    public SI add(Map<String, Object> valueMap) {
        if (valueMap != null) this.valueMap.putAll(valueMap);
        return this;
    }

    public SI add(Object... kvs) {
        Map<String, Object> kvMap = toMap(false, false, kvs);
        return this.add(kvMap);
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

    private static Map<String, Object> toMap(boolean withSuffix, boolean needTrim, Object... kvs) {
        Map<String, Object> kvMap = new HashMap<>();
        if (kvs == null || kvs.length == 0) return kvMap;
        verifyPairWithStringKey(kvs);
        for (int i = 0; i < kvs.length; i++) {
            if (i % 2 == 0) {
                String k = (String) kvs[i];
                if (withSuffix) {
                    if (k.endsWith(" ->")) {
                        String realKey = k.substring(0, k.length() - 3);
                        realKey = needTrim ? realKey.trim() : realKey;
                        kvMap.put(realKey, kvs[i + 1]);
                    }else {
                        throw new UnexpectedParameterException("Index: " + i + ". This parameter is a key, the key must be end with \" ->\". ");
                    }
                }else {
                    kvMap.put(k, kvs[i + 1]);
                }

            }
        }
        return kvMap;
    }

    private static void verifyPairWithStringKey(Object... kvs) {
        if (kvs != null) {
            if (kvs.length % 2 != 0)
                throw new RuntimeException("The parameters length must be even. 参数个数必须为偶数。");
            for (int i = 0; i < kvs.length; i++) {
                if (i % 2 == 0) {
                    if (kvs[i] == null) throw new NullPointerException("Index: " + i + ". This parameter is a key, the key must be not null. ");
                    try {
                        String k = (String) kvs[i];
                    } catch (ClassCastException castException) {
                        throw new ClassCastException("Index: " + i + ". This parameter is a key, the key must be `String` type. ");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
