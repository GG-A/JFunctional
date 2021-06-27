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

import com.github.gg_a.tuple.Tuple;
import com.github.gg_a.tuple.Tuple2;
import com.github.gg_a.tuple.Tuple3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracting expression from ${}
 *
 * @author GG-A
 * @since 0.8.0
 */
public class StringExtractor {

    /**
     * ${} will parse to $ (String literal). <br>
     * 遇到 ${} 则解析成字符 $
     */
    private final static String $ = "${}";
    private final static String DEFAULT_VALUE_DELIMITER = ": ";

    /**
     * Regex for extracting expression from ${}, but { or } can't be included in ${}
     */
    private final static Pattern PATTERN = Pattern.compile("\\$\\{((?![{}]).)*}", Pattern.MULTILINE);

    /**
     * 根据 ${} 分割字符串
     *
     * @param source 待插值的字符串
     * @return 列表。Tuple3：_1 字符串类型； _2 字符串； _3 原始字符串
     */
    public static List<Tuple3<StringType, String, String>> split(String source) {
        List<Tuple3<StringType, String, String>> stringTs = new ArrayList<>();
        final Matcher matcher = PATTERN.matcher(source);

        int startIndex = 0;
        while (matcher.find()) {
            String matchStr = matcher.group();
            int start = matcher.start();    // 起始字符位置
            int end = matcher.end();        // 结束字符+1 的位置

            if ($.equals(matchStr)) {
                String value = source.substring(startIndex, start + 1);
                stringTs.add(Tuple.of(StringType.STRING, value, value));
            } else {
                if (start != startIndex) {  // 不相等说明${}前面有一段常量还未添加进列表
                    String value = source.substring(startIndex, start);
                    stringTs.add(Tuple.of(StringType.STRING, value, value));
                }

                String strInBrace = matchStr.substring(2, matchStr.length() - 1);
                int index = strInBrace.indexOf(DEFAULT_VALUE_DELIMITER);
                if (index > -1) {
                    String defaultValue = "";
                    if (index + DEFAULT_VALUE_DELIMITER.length() < strInBrace.length()) {
                        defaultValue = strInBrace.substring(index + DEFAULT_VALUE_DELIMITER.length());
                        stringTs.add(Tuple.of(StringType.VALUE, strInBrace.substring(0, index), defaultValue));
                    }
                }else {
                    stringTs.add(Tuple.of(StringType.VALUE, strInBrace, matchStr));
                }
            }
            startIndex = end;
        }

        if (startIndex < source.length()) {
            String value = source.substring(startIndex);
            stringTs.add(Tuple.of(StringType.STRING, value, value));
        }

        return stringTs;
    }
}
