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

import com.github.gg_a.base.None;
import com.github.gg_a.function.R1;
import com.github.gg_a.pattern.PatternIn;

import java.util.Objects;

/**
 * ActionNone RMatcher
 *
 * @since 0.8.6
 */
public class ActionNoneRMatcher<P, R> extends SimpleRMatcher<None, P, P, R> {

    private final R1<P, Boolean> preAction;

    public ActionNoneRMatcher(None value, boolean isMatch, R1<P, Boolean> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionNoneRMatcher(None value, R1<P, Boolean> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionNoneRMatcher<P, R> when(P value, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            returnValue = action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> whenNext(P value, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            returnValue = action.$(value);
        }
        return this;
    }

    public ActionNoneRMatcher<P, R> when(boolean value, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(null);
        }
        return this;
    }

    public ActionNoneRMatcher<P, R> whenNext(boolean value, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(null);
        }
        return this;
    }

    public ActionNoneRMatcher<P, R> when(PatternIn<P> values, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    public ActionNoneRMatcher<P, R> whenNext(PatternIn<P> values, R1<P, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public R orElse(R1<None, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(null);
        }
        return returnValue;
    }
}
