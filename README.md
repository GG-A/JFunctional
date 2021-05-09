# JFunctional
提供更简单更好用的Java函数式编程接口 (Java Functional Interface that more simpler and easier to use)；  
提供元组（tuple）类型支持；  
**兼容Java 8及Java 9+模块化系统**；  


## Environment（开发环境）  
+ JDK 9.0.4
+ IntelliJ IDEA 2021.1 (Community Edition)
+ Apache maven 3.6.1


## 集成方式（兼容Java 8及Java 9+）
### Maven
```xml
<dependency>
  <groupId>com.github.GG-A</groupId>
  <artifactId>JFunctional</artifactId>
  <version>0.5.3</version>
</dependency>
```

### Gradle
```
implementation 'com.github.GG-A:JFunctional:0.5.3'
```


## 使用指南（User Guide）

- [JFunctional与函数式接口](#jfunctional与函数式接口)
  - [Java函数式接口说明](#java函数式接口说明)
  - [JFunctional函数式接口使用](#jfunctional函数式接口使用)
- [Tuple（元组）](#tuple元组)
  - [Tuple（元组）使用](#tuple元组使用)
  - [EasyTuple 使用](#easytuple-使用)

## JFunctional与函数式接口

### Java函数式接口说明
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
以下是4种类别的接口说明：

| Vn | 含义 |
| :----:| :----: |
| **V0** | 无参数，无返回值 (a function that accepts 0 argument and returns no result) | 
| **V1** | 1个参数，无返回值 (a function that accepts 1 argument and returns no result) | 
| **...** | ...... | 
| **V9** | 9个参数，无返回值 (a function that accepts 9 arguments and returns no result) | 

| Rn | 含义 |
| :----:| :----: |
| **R0** | 无参数，但有返回值 (a function that accepts 0 argument and produces a result) | 
| **R1** | 1个参数，且有返回值 (a function that accepts 1 argument and produces a result) | 
| **...** | ...... | 
| **R9** | 9个参数，且有返回值 (a function that accepts 9 arguments and produces a result) | 

| VTn | 含义 |
| :----:| :----: |
| **VT0** | 无参数，无返回值且抛出异常 (accepts 0 argument and returns no result, and will throw exception) | 
| **VT1** | 1个参数，无返回值且抛出异常 (accepts 1 argument and returns no result, and will throw exception) | 
| **...** | ...... | 
| **VT9** | 9个参数，无返回值且抛出异常 (accepts 9 arguments and returns no result, and will throw exception) | 

| RTn | 含义 |
| :----:| :----: |
| **RT0** | 无参数，但有返回值且抛出异常 (accepts 0 argument and produces a result, and will throw exception) | 
| **RT1** | 1个参数，有返回值且抛出异常 (accepts 1 argument and produces a result, and will throw exception) | 
| **...** | ...... | 
| **RT9** | 9个参数，有返回值且抛出异常 (accepts 9 arguments and produces a result, and will throw exception) | 


### JFunctional函数式接口使用  
- V2接口示例  
```java
public void testV2(){
    /*
     Java 8之前：使用匿名内部类，调用v2AsParams
     */
    v2AsParams(new V2<String, String>() {
        @Override
        public void $(String s1, String s2) {
            System.out.println(s1 + " -- " + s2);
        }
    });


    /*
     Java 8 及以后：使用 Lambda 表达式，调用v2AsParams
     */
    v2AsParams((s1, s2) -> System.out.println(s1 + " -- " + s2));

}

// 当一个函数需要接收一个 `两个参数无返回值的函数接口` 时，可以使用现有的 V2<T1, T2>，而不用重新构造一个接口
private void v2AsParams(V2<String, String> v2) {
    v2.$("abcd", "1234");
}
```

- R1接口示例  
```java
public void testR1() {
    List<String> ls = Arrays.asList("1", "2", "3", "4");
    /*
     Java 8之前：使用匿名内部类，调用 map
     */
    List<Integer> intList = map(ls, new R1<String, Integer>() {
        @Override
        public Integer $(String s) {
            return Integer.valueOf(s) + 10;
        }
    });
    System.out.println(intList);      // 输出：[11, 12, 13, 14]

    /*
    Java 8 及以后：使用 Lambda 表达式，调用 map
     */
    List<Integer> map = map(ls, s -> Integer.valueOf(s) + 20);
    System.out.println(map);         // 输出：[21, 22, 23, 24]

}

// 当一个函数需要接收一个 `接收一个参数，并返回值的函数接口` 时，可以使用 R1<T, R>，不用重新构造一个接口，
// 如：java.util.stream.Stream 中的 map 函数
private <T, R> List<R> map(List<T> ls, R1<T, R> r1) {
    ArrayList<R> rs = new ArrayList<>();
    for (T l : ls)
        rs.add(r1.$(l));

    return rs;
}
```

- R2接口（不支持抛出异常） 处理异常示例  
```java
public void testR2Exception(){
    // 必须在 lambda 表达式中使用 try-catch 块处理，无法将异常继续向外抛出
    R2<String, Integer, String> r2 = (s, i) -> {
        if (i == 5) {
            try {
                // 必须使用 try-catch 处理，否则报错
                throw new IOException("抛出异常");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s + i;
    };

    // 由于R2不支持抛出异常，所以调用 $函数没有异常
    String s = r2.$("abcd", 1);
}
```

- RT2接口（支持抛出异常） 处理异常示例  
```java
public void testRT2Exception() throws IOException { 
    RT2<String, Integer, String, IOException> rt2 = (s, i) -> {
        // 使用 RT2 在lambda 表达式中，不用处理异常，等到调用 $ 函数时再处理
        if (i == 5) throw new IOException("抛出异常");
        return s + i;
    };
    /*
    第一种方式：使用 try-catch 处理异常
     */
    try {
        String s = rt2.$("abcd", 1);
    } catch (IOException e) {
        e.printStackTrace();
    }

    /*
     第二种方式：继续向外抛出异常，在函数上申明异常：  public void rt2_exception() throws IOException
     */
    // String s1 = rt2.$("1234", 5);
    String s2 = rt2.$("1234", 56);
}
```



## Tuple（元组）
元组（Tuple）是用来表示一组数据的集合。与列表（List）类似，但与列表有着本质的区别：
1. 元组可以存放不同类型的数据，而列表只能存放相同类型的数据
2. 元组的值一经初始化，无法修改，只能查看

Java中一直没有提供元组（Tuple）类型的支持，导致有些时候，简单的问题复杂化，特别是当**一个方法需要返回多个值，且这些值的类型不一致**时，采用元组（Tuple）可以提供极大的便利。为此，JFunctional 提供 **Tuple0 ~ Tuple9** 这 10 种 Tuple 类型。

### Tuple（元组）使用
- 创建元组与取出元组中的元素
```java
Tuple3<String, Integer, Tuple2<String, String>> t3 = new Tuple3<>("zs", 20, new Tuple2<String, String>("123", "abc"));
System.out.println(t3._1);    // 输出: zs
System.out.println(t3._2);    // 输出: 20
System.out.println(t3._3);    // 输出: ("123", "abc")
```

- 为元组中的元素起别名以及通过别名取元素
```java
// 方式一（推荐）
// MyTupleAlias.java
package mypackage;
import com.github.gg_a.tuple.TupleAlias;

public enum MyTupleAlias implements TupleAlias {
     $USER_ALIAS$,
          ID, NAME, TEL, AGE, BIRTHDAY, PROVINCE, CITY, REGISTERTIME,
     $ORDER_ALIAS$,
          ORDERID, GOODSID, USERID, PRICE, QUANTITY, ORDERTIME, PAYTIME
}

// Test.java
package test.xxx;
import static mypackage.MyTupleAlias.*;

Tuple3<Integer, String, Integer> userInfo = new Tuple3<>(1, "Tom", 20);
userInfo.alias(ID, NAME, AGE);
Integer id  = userInfo.__(ID);     // （推荐）使用枚举值取tuple中的元素
String name = userInfo.__("NAME");   // （不推荐）使用枚举值对应的字符串取tuple中的元素
System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

// 方式二
Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("name", "age");
String name = (String)t2.__("name");    // 不使用泛型参数
Integer age = t2.<Integer>__("age");    // 使用泛型参数
System.out.println(name);               // 输出: abc
System.out.println(age);                // 输出: 20

```

- 遍历元组中的元素
```java
Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("name", "age");
for (int i = 0; i < t2.arity(); i++) {
    Object element = t2.element(i);                                     // 不带别名
    System.out.println(element);                                        // 输出：zs  和   20
    Tuple2<String, Object> elementWithAlias = t2.elementWithAlias(i);   // 带别名
    System.out.println(elementWithAlias);                               // 输出：("name", "zs")   和  ("age", 20)
}
```

- 方法中返回多个值
```java
public Tuple2<String, Integer> returnMultipleValue(){
    String name = "zs";
    Integer age = 20;
    
    return new Tuple2<>(name, age);   // 把 String 和 Integer 的数据一起返回
}
```

- 配合 Java10 的局部变量自动类型推断(Auto Type Inferring)会更好哦
```java
// Java 8 语法
Tuple9<String, Integer, Tuple1<String>, String, Integer, String, Integer, Tuple2<String, String>, String> tuple91 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String) null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");

// Java 10及以上语法(var)
var tuple9 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String)null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");
```

### EasyTuple 使用
EasyTuple是简单版的Tuple，在**所有元素都是相同类型**的情境下使用，和列表（List）很像，但是使用起来比列表（List）更方便一些  

```java
EasyTuple8<String> et8 = new EasyTuple8<>("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8");
System.out.println(et8);  // 输出: ("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8")
EasyTuple8<String> alias = et8.alias(null, "", "testTuple", "abc", "5", "第6个", "7", "8");
System.out.println(alias);  // 输出: (null: "abcdefg", : "abc", testTuple: "bcd", abc: null, 5: "29", 第6个: "tupel6", 7: "666", 8: "tuple8")
String s = et8.__((String) null);
System.out.println(s);
String s1 = et8.__("");
System.out.println(s1);
System.out.println(et8.__("第6个"));
System.out.println(et8._5);
for (int i = 0; i < et8.arity(); i++) {
    Tuple2<String, String> t2 = et8.elementWithAlias(i);
    System.out.println("t2(" + i + "): " + t2);
}
```


## IntelliJ IDEA 智能提示
由于接口名过于简单，导致 IntelliJ IDEA 智能提示不是很友好，对于**只有一个字母的接口名**，可能无法智能提示，解决办法：  
1. 使用智能补全快捷键（设置方法，进入IDEA快捷键设置Keymap：**Main menu > Code > Completion > Basic**），我设置的快捷键是：**alt + /** ，以 **V1** 为例：  
a. 输入 v1，会发现没有 **V1 接口**的提示  
![IDEA 智能提示](https://github.com/GG-A/JFunctional/blob/master/images/IDEA%20Smart%20tips%201.png)  
b. 此时，按下 **alt + /**，就会有 **V1 接口**的提示  
![IDEA 智能提示](https://github.com/GG-A/JFunctional/blob/master/images/IDEA%20Smart%20tips%202.png)  

2. 手动导入`function`下的所有接口（但是对于**只有一个字母的接口名**依然无法智能提示，只是手动输入的时候，可以避免出现 由于没有导包导致的错误）  
`import com.github.gg_a.function.*;`



## 点个赞哟
如果你喜欢 JFunctional，感觉 JFunctional 帮助到了你，可以点右上角 **Star** 支持一下哦，感谢感谢！

## Copyright

   **Copyright 2019 GG-A**, < 2018158885@qq.com, https://github.com/GG-A/JFunctional >
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.



