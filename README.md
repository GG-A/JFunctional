# JFunctional
Java函数式编程接口

## JFunctional与函数式接口
关于**函数式接口**，Java 8标准中也有提供，在`java.util.function`下，总共包含43个接口，这些接口是为了让**Lamdba函数表达式**使用的更加简便。总共包含以下几类接口：

| 接口类型 | 表示 |
| :-----| :----- |
| **Consumer** | 有输入参数，但无返回结果的函数 | 
| **Function** | 有输入参数，并返回结果的函数 | 
| **Predicate** | 有输入参数，并返回boolean类型的结果的函数 | 
| **Supplier** | 不提供参数，但返回结果的函数 | 
| **Operator** | 有输入参数，并返回与参数类型一致的结果 | 

可以看到Java 8标准中提供的函数式接口还是挺丰富的，但是个人感觉使用起来有三个不便的地方：
1. 类别太多，且每个类别下又有几个或者十几个接口，命名不一，不方便记忆
2. 如果有仔细观察过这些函数式接口，会发现这些接口所表示的函数最多只有两个参数，如果要使用3个及以上的函数，就要自己构造
3. 对于抛出异常的函数或Lambda表达式无法很好的支持，必须使用try catch才能正常使用，而不能继续向外抛出异常

#### 而JFunctional提供了4种类别、40个基础的函数式接口，涵盖了以上5种类别所提供的所有函数，且将原本仅支持 2个参数 扩展到多达 9个参数 的函数，并扩展了支持抛出异常的函数式接口。
**4种类别：**

+ **V** (**V**oid)系：表示无返回值的函数

+ **R** (**R**eturn)系：表示有返回值的函数

+ **VT** (**V**oid and **T**hrow exception)系：表示无返回值且抛出异常的函数

+ **RT** (**R**eturn and **T**hrow exception)系：表示有返回值且抛出异常的函数

采用数字结尾，数字表示的是函数的参数个数，分别提供 **0 ~ 9** 个参数的函数，方便记忆。
以下是所有40个接口说明：

| 函数接口 | 含义 |
| :----:| :----- |
| **V0** | 无参数，无返回值 (a function that accepts 0 argument and returns no result) | 
| **V1** | 接收1个参数，无返回值 (a function that accepts 1 argument and returns no result) | 
| **V2** | 接收2个参数，无返回值 (a function that accepts 2 arguments and returns no result) | 
| **V3** | 接收3个参数，无返回值 (a function that accepts 3 arguments and returns no result) | 
| **V4** | 接收4个参数，无返回值 (a function that accepts 4 arguments and returns no result) | 
| **V5** | 接收5个参数，无返回值 (a function that accepts 5 arguments and returns no result) | 
| **V6** | 接收6个参数，无返回值 (a function that accepts 6 arguments and returns no result) | 
| **V7** | 接收7个参数，无返回值 (a function that accepts 7 arguments and returns no result) | 
| **V8** | 接收8个参数，无返回值 (a function that accepts 8 arguments and returns no result) | 
| **V9** | 接收9个参数，无返回值 (a function that accepts 9 arguments and returns no result) | 
| **R0** | 无参数，但有返回值 (a function that accepts 0 argument and produces a result) | 
| **R1** | 接收1个参数，且有返回值 (a function that accepts 1 argument and produces a result) | 
| **R2** | 接收2个参数，且有返回值 (a function that accepts 2 arguments and produces a result) | 
| **R3** | 接收3个参数，且有返回值 (a function that accepts 3 arguments and produces a result) | 
| **R4** | 接收4个参数，且有返回值 (a function that accepts 4 arguments and produces a result) | 
| **R5** | 接收5个参数，且有返回值 (a function that accepts 5 arguments and produces a result) | 
| **R6** | 接收6个参数，且有返回值 (a function that accepts 6 arguments and produces a result) | 
| **R7** | 接收7个参数，且有返回值 (a function that accepts 7 arguments and produces a result) | 
| **R8** | 接收8个参数，且有返回值 (a function that accepts 8 arguments and produces a result) | 
| **R9** | 接收9个参数，且有返回值 (a function that accepts 9 arguments and produces a result) | 
| **VT0** | 无参数，无返回值且抛出异常 (a function that accepts 0 argument and returns no result, and will throw exception) | 
| **VT1** | 接收1个参数，无返回值且抛出异常 (a function that accepts 1 argument and returns no result, and will throw exception) | 
| **VT2** | 接收2个参数，无返回值且抛出异常 (a function that accepts 2 arguments and returns no result, and will throw exception) | 
| **VT3** | 接收3个参数，无返回值且抛出异常 (a function that accepts 3 arguments and returns no result, and will throw exception) | 
| **VT4** | 接收4个参数，无返回值且抛出异常 (a function that accepts 4 arguments and returns no result, and will throw exception) | 
| **VT5** | 接收5个参数，无返回值且抛出异常 (a function that accepts 5 arguments and returns no result, and will throw exception) | 
| **VT6** | 接收6个参数，无返回值且抛出异常 (a function that accepts 6 arguments and returns no result, and will throw exception) | 
| **VT7** | 接收7个参数，无返回值且抛出异常 (a function that accepts 7 arguments and returns no result, and will throw exception) | 
| **VT8** | 接收8个参数，无返回值且抛出异常 (a function that accepts 8 arguments and returns no result, and will throw exception) | 
| **VT9** | 接收9个参数，无返回值且抛出异常 (a function that accepts 9 arguments and returns no result, and will throw exception) | 
| **RT0** | 无参数，但有返回值且抛出异常 (a function that accepts 0 argument and produces a result, and will throw exception) | 
| **RT1** | 接收1个参数，有返回值且抛出异常 (a function that accepts 1 argument and produces a result, and will throw exception) | 
| **RT2** | 接收2个参数，有返回值且抛出异常 (a function that accepts 2 arguments and produces a result, and will throw exception) | 
| **RT3** | 接收3个参数，有返回值且抛出异常 (a function that accepts 3 arguments and produces a result, and will throw exception) | 
| **RT4** | 接收4个参数，有返回值且抛出异常 (a function that accepts 4 arguments and produces a result, and will throw exception) | 
| **RT5** | 接收5个参数，有返回值且抛出异常 (a function that accepts 5 arguments and produces a result, and will throw exception) | 
| **RT6** | 接收6个参数，有返回值且抛出异常 (a function that accepts 6 arguments and produces a result, and will throw exception) | 
| **RT7** | 接收7个参数，有返回值且抛出异常 (a function that accepts 7 arguments and produces a result, and will throw exception) | 
| **RT8** | 接收8个参数，有返回值且抛出异常 (a function that accepts 8 arguments and produces a result, and will throw exception) | 
| **RT9** | 接收9个参数，有返回值且抛出异常 (a function that accepts 9 arguments and produces a result, and will throw exception) | 

## 集成方式
### Maven
```
<dependency>
  <groupId>com.github.GG-A</groupId>
  <artifactId>JFunctional</artifactId>
  <version>0.0.4</version>
</dependency>
```

### Gradle
`implementation 'com.github.GG-A:JFunctional:0.0.4'`

## Copyright

   **Copyright 2019 GG-A**, <2018158885@qq.com>
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.



