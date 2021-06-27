package com.github.gg_a;

import com.github.gg_a.text.SI;
import com.github.gg_a.tuple.Tuple;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GG
 * @version 1.0
 */
public class InterpolatorTest {

    @Test
    public void testInterpolator() {
        Tuple t1 = Tuple.of("zs", 123456).alias("NAME", "ID");
        Tuple t2 = Tuple.of(20, "tom", 190.5).alias("age", "nickName", "height");
        // ${nick\nName: Jack}: ${} can't contain \n, it will be ignore
        String parse = SI.of(t1, t2).$("${NAME}--${age}--${nickName}--${nick\nName: Jack}--${ID}--${height}");

        assertEquals("zs--20--tom--${nick\nName: Jack}--123456--190.5", parse);
    }

    @Test
    public void testInterpolator1() {
        SI si = Tuple.of("zs", 20, "tom", 190.5, 123456).alias("name", "age", "nickName", "height", "id").toSI();

        String parse = si.$("${name}--${age}--${nickName}--${id}--${height}");

        assertEquals("zs--20--tom--123456--190.5", parse);
    }

    @Test
    public void testDefaultValue() {
        // use ": " set default value
        String source = "${NAME}--${NAME: tom}--${age: 20}--${ID1:}" +
                        "--${ ID1 }--${ID1: }--${id1}--${age::20}--${ID}" +
                        "--${ ID1:  }--${ID: 123456}";
        Tuple t1 = Tuple.of("zs", null).alias("NAME", "ID");
        SI si = SI.of(t1, null, Tuple.empty());
        String parse = si.$(source);
        System.out.println(parse);

        assertEquals("zs--zs--20--${ID1:}--${ ID1 }----${id1}--${age::20}--null-- --null", parse);

    }

    @Test
    public void testMetaChar() {
        Tuple t = Tuple.of("zs", 123456).alias("NAME", "ID");
        SI si = SI.of(t);
        String source = "${NAME}--$${ID}--$$$${ID}--${}{ID}--${}";  // ${} will be parsed $
        String parse = si.$(source);
        assertEquals("zs--$123456--$$$123456--${ID}--$", parse);
    }

    @Test
    public void testTupleToSI() {
        String source = "${NAME}--${age: 18}--${nickName}--${ID}--${height}--${_1}--${_2}";

        SI si0 = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
        String parse = si0.$(source);
        assertEquals("zs--18--${nickName}--123456--${height}--${_1}--${_2}", parse);

        SI si1 = Tuple.of("zs", 123456).toSI();   // alias is _1, _2
        parse = si1.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--${height}--zs--123456", parse);

        /*
         * add, del, set
         */
        SI si = Tuple.of().toSI();
        parse = si.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--${height}--${_1}--${_2}", parse);


        Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
        si.add(t2);         // add
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("height", 175);
        si.add(hashMap);    // add
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--175--${_1}--${_2}", parse);

        si.del("age", "nickName");  // delete
        parse = si.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--175--${_1}--${_2}", parse);

        Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
        si.set(t3);             // set
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);
    }

    @Test
    public void testAddSetDel() {
        String source = "${NAME}--${age}--${nickName}--${ID}--${height}";

        Tuple t1 = Tuple.of("zs", 123456).alias("NAME", "ID");
        SI si = SI.of(t1);
        String parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--${age}--${nickName}--123456--${height}", parse);

        Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
        si.add(t2);         // add
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--20--tom--123456--${height}", parse);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("height", 175);
        si.add(hashMap);    // add
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--20--tom--123456--175", parse);

        si.del("age", "nickName");  // delete
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--${age}--${nickName}--123456--175", parse);

        Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
        si.set(t3);             // set
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("${NAME}--20--tom--${ID}--${height}", parse);

    }

    @Test
    public void testNull() {
        SI si = new SI(null, Tuple.empty());
        SI si1 = new SI((Tuple) null);
        SI si2 = Tuple.of(null).toSI();
    }
}
