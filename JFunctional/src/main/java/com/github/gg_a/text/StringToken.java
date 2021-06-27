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

/**
 * String Token. <br>
 * @since 0.8.1
 */
public class StringToken {
    private StringType type;
    private String value;
    private String originValue;

    public StringToken() {
    }

    public StringToken(StringType type, String value, String originValue) {
        this.type = type;
        this.value = value;
        this.originValue = originValue;
    }

    public StringType getType() {
        return type;
    }

    public void setType(StringType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", originValue='" + originValue + '\'' +
                '}';
    }
}
