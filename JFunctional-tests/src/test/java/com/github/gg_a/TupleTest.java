package com.github.gg_a;

import com.github.gg_a.tuple.Tuple2;
import com.github.gg_a.tuple.Tuple3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.gg_a.MyTupleAlias.*;

/**
 * @author GG
 */
public class TupleTest {

    @Test
    public void testTupleAlias1() {
        Tuple3<Integer, String, Integer> userInfo = new Tuple3<>(1, "Tom", 20);
        userInfo.alias(ID, NAME, AGE);
        Integer id  = userInfo.__(ID);             // （推荐）  使用枚举值取tuple中的元素
        String name = userInfo.__("NAME");    // （不推荐）使用枚举值对应的字符串取tuple中的元素
        System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

        assertEquals(1, id);
        assertEquals("Tom", name);
    }

    @Test
    public void testTupleAlias2() {
        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("name", "age");
        Integer age = t2.<Integer>__("age");
        System.out.println(age);                // output: 20
        assertEquals(20, age);
    }

    @Test
    public void testForeachElements() {
        Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("name", "age");
        for (int i = 0; i < t2.arity(); i++) {
            Object element = t2.element(i);                  // 不带别名
            System.out.println(element);                     // output：zs  和   20
            Tuple2<String, Object> elementWithAlias = t2.elementWithAlias(i);   // 带别名
            System.out.println(elementWithAlias);            // output：("name", "zs")   和  ("age", 20)
        }

        System.out.println(t2);
        assertEquals("(name: \"zs\", age: 20)", t2.toString());

    }

}
