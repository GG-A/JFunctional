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
import com.github.gg_a.function.V1;
import com.github.gg_a.pattern.PatternIn;

import java.util.Objects;

/**
 * ActionNone VMatcher
 *
 * @since 0.8.6
 */
public class ActionNoneVMatcher<P> extends SimpleVMatcher<None, P, P> {

    private final R1<P, Boolean> preAction;

    public ActionNoneVMatcher(None value, boolean isMatch, R1<P, Boolean> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionNoneVMatcher(None value, R1<P, Boolean> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionNoneVMatcher<P> when(P value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> whenNext(P value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            action.$(value);
        }
        return this;
    }

    public ActionNoneVMatcher<P> when(PatternIn<P> values, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    public ActionNoneVMatcher<P> whenNext(PatternIn<P> values, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public Void orElse(V1<None> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(null);
        }
        return returnValue;
    }
}
