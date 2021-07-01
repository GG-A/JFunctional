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
import com.github.gg_a.pattern.PatternIn;
import com.github.gg_a.pattern.type.*;
import java.util.List;
import java.util.Objects;


/**
 * String Matcher with return value
 * @since 0.7.2
 */
public class StringRMatcher<R> extends SimpleRMatcher<String, String, String, R> {

    private PatternString patternString;
    private String ucValue;     // Upper Case Value
    private boolean ignoreCase = false;

    public StringRMatcher(String value, boolean isMatch, PatternString patternString) {
        this(value, isMatch);
        this.patternString = patternString;

        ucValue = value;
        if (value != null){
            if (patternString == PatternString.ICCONTAIN
                    || patternString == PatternString.ICPREFIX
                    || patternString == PatternString.ICSUFFIX){
                ucValue = value.toUpperCase();
                ignoreCase = true;
            }
        }
    }

    public StringRMatcher(String value, boolean isMatch) {
        super(value, isMatch);
    }

    public StringRMatcher(String value) {
        super(value);
    }

    @Override
    public StringRMatcher<R> when(String value, R1<String, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);

            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public StringRMatcher<R> whenNext(String value, R1<String, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);

            if (value == null || this.value == null) {
                if (this.value == value) {
                    returnValue = action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) returnValue = action.$(this.value);
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) returnValue = action.$(this.value);
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) returnValue = action.$(this.value);
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) returnValue = action.$(this.value);
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) returnValue = action.$(this.value);
            }
        }

        return this;
    }

    public StringRMatcher<R> when(PatternIn<String> values, R1<String, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
                return this;
            }

            if (values != null) {
                List<String> vs = values.getVs();
                for (String e : vs) {
                    if (e != null) {
                        when(e, action);
                        if (isMatch) break;
                    }
                }
            }
        }
        return this;
    }

    public StringRMatcher<R> whenNext(PatternIn<String> values, R1<String, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    returnValue = action.$(this.value);
                }
                return this;
            }

            if (values != null) {
                List<String> vs = values.getVs();
                for (String e : vs) {
                    if (e != null) {
                        whenNext(e, action);
                        if (isMatch) break;
                    }
                }
            }
        }
        return this;
    }


    @Override
    public R orElse(R1<String, R> action) {
        if (!isMatch) {
            Objects.requireNonNull(action);
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
