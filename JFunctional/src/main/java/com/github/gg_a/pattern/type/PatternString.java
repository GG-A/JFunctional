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
package com.github.gg_a.pattern.type;

/**
 * Pattern String Type
 * @since 0.7.2
 */
public enum PatternString implements PatternTypes {
    STRING,
    IGNORECASE,
    CONTAIN,
    PREFIX,
    SUFFIX,
    ICCONTAIN,  // ignore case for contain
    ICPREFIX,   // ignore case for prefix
    ICSUFFIX    // ignore case for suffix
}
