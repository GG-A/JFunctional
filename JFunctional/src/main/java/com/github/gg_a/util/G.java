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
package com.github.gg_a.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Global Variables And Methods
 *
 * @since 0.8.6
 */
public class G {
    /**
     * Whether object array contains {@code null} value. <br>
     * 数组中是否包含{@code null}值
     * @param objects object array
     * @return {@code true} if objects contains {@code null} value
     * @since 0.8.6
     */
    public static boolean hasNull(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return false;
        boolean hasNull = false;
        for (Object obj : objects) {
            if (obj == null) {
                hasNull = true;
                break;
            }
        }
        return hasNull;
    }

    /**
     * Whether string array contains {@code null} value or {@code ""} empty value. <br>
     * 数组中包含{@code null}值或者空字符串{@code ""}，则返回true
     * @param strs string array
     * @return {@code true} if strings contains {@code null} value or {@code ""} empty value
     * @since 0.8.6
     */
    public static boolean hasEmpty(String... strs) {
        if (strs == null) return true;
        if (strs.length == 0) return false;
        boolean hasEmpty = false;
        for (String str : strs) {
            if (isEmpty(str)) {
                hasEmpty = true;
                break;
            }
        }
        return hasEmpty;
    }

    /**
     * {@code true} if all array values are {@code null}. <br>
     * 数组中所有的值都是{@code null}，则返回{@code true}
     * @param objects object array
     * @return {@code true} if all array values are {@code null}
     * @since 0.8.6
     */
    public static boolean allNull(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return false;
        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

    /**
     * {@code true} if all array values are {@code null} or {@code ""} empty value. <br>
     * 数组中所有的值都是{@code null}或者空字符串{@code ""}，则返回{@code true}
     * @param strs string array
     * @return {@code true} if all array values are null or {@code ""} empty value
     * @since 0.8.6
     */
    public static boolean allEmpty(String... strs) {
        if (strs == null) return true;
        if (strs.length == 0) return false;
        return Arrays.stream(strs).allMatch(G::isEmpty);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

}
