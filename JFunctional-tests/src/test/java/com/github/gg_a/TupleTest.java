package com.github.gg_a;

import com.github.gg_a.exception.AliasDuplicateException;
import com.github.gg_a.exception.NumberOfAliasesException;
import com.github.gg_a.tuple.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.gg_a.MyTupleAlias.*;

/**
 * @author GG
 */
public class TupleTest {

    @Test
    public void testTupleAlias() {
        Tuple4<Integer, String, String, Integer> userInfo = new Tuple4<>(1, "Tom", "nullval", 20);
        userInfo.alias(ID, NAME, null, AGE);
        Integer id  = userInfo.__(ID);             // （推荐）  使用枚举值取tuple中的元素
        String name = userInfo.__("NAME");    // （不推荐）使用枚举值对应的字符串取tuple中的元素
        String nullVal = userInfo.__((TupleAlias) null);
        String nullVal1 = userInfo.__((String) null);
        System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

        assertEquals(1, id);
        assertEquals("Tom", name);
        assertEquals("nullval", nullVal);
        assertEquals("nullval", nullVal1);

        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("NAME", "AGE");
        Integer age = t2.<Integer>__("AGE");
        String name1 = t2.__(NAME);
        System.out.println(age);                // output: 20
        assertEquals(20, age);
        assertEquals("abc", name1);

        Tuple2<String, Integer> zsTuple = new Tuple2<>("zs", 12);
        assertThrows(NumberOfAliasesException.class, () -> zsTuple.alias(A, B, C));
        assertThrows(AliasDuplicateException.class, () -> zsTuple.alias(A, B, B));
    }

    @Test
    public void testCopyAliases() {
        Tuple3<String, Integer, Integer> t3 = new Tuple3<>("a", 1, 2).alias(A, B, C);

        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("NAME", "AGE");
        Tuple2<String, Integer> t21 = new Tuple2<>("abc", 20).alias(NAME, AGE);
        Tuple2<String, Integer> t22 = new Tuple2<>("111", 15);
        Tuple2<String, Integer> t23 = new Tuple2<>("111", 15);

        assertEquals("tuple", t3.aliasType());
        System.out.println(t3);
        t3.copyAliases(t2);     // 类型不匹配，不做任何操作
        assertEquals("tuple", t3.aliasType());
        System.out.println(t3);
        t22.copyAliases(t2);
        assertEquals("string", t22.aliasType());
        System.out.println(t22);
        t23.copyAliases(t21);
        assertEquals("tuple", t23.aliasType());
        System.out.println(t23);



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

    @Test
    public void testTupleEquals() {
        assertTrue(Tuple.of().equals(Tuple0.instance()));
        assertFalse(new Tuple2<>("1", "a").equals(Tuple0.instance()));
        assertTrue(Tuple.of(new Tuple2<>("zs", 123)).equals(new Tuple1<>(Tuple.of("zs", 123))));
        assertTrue(Tuple.of(new Tuple2<>("zs", null)).equals(new Tuple1<>(Tuple.of("zs", null))));
        assertFalse(Tuple.of(new Tuple2<>("zs", 123), 123).equals(new Tuple1<>(Tuple.of("zs", 123))));
        assertTrue(new Tuple2<>("zs", null).equals(new Tuple2<>("zs", null)));
        assertFalse(new Tuple2<>("zs", null).equals(new Tuple2<>("zs", 123)));
        assertFalse(new Tuple2<>("zs", null).equals(null));
        assertFalse(new Tuple2<>("zs", null).equals(new Object()));
        // ================带别名的匹配================
        Tuple2<String, Object> zs = new Tuple2<>("zs", null);
        Tuple2<String, Object> zsWithAlias = new Tuple2<>("zs", null).alias(A, B);
        assertFalse(new Tuple2<>("zs", null).alias(A, B).equals(zs));
        assertTrue(zsWithAlias.equals(zs.copyAliases(zsWithAlias)));

        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple1 = new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null));
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple2 = new Tuple4<>(1, Tuple0.instance(), null, zsWithAlias);
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple3 = new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null).alias(A, B));
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple4 = new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null).alias("A", "B"));
        assertFalse(tuple1.equals(tuple2));
        assertTrue(tuple2.equals(tuple3));
        assertFalse(tuple3.equals(tuple4));


        System.out.println("testTupleEquals 全部成功！");
    }

}
