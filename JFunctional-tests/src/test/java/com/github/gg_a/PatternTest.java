package com.github.gg_a;

import com.github.gg_a.pattern.PatternIn;
import com.github.gg_a.tuple.Tuple;
import com.github.gg_a.tuple.Tuple2;
import org.junit.jupiter.api.Test;
import java.util.Objects;

import static com.github.gg_a.pattern.Pattern.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GG
 */
public class PatternTest {

    @Test
    public void testPatternValue1() {
        String s = "5";
        String result = match(s)
                .when("1", v -> v + v)
                .when("2", v -> v + "a")
                .when(in("3", "4", "5", "6"), v -> v + " - abcd")
                .orElse(v -> "no match");

        System.out.println("match result: " + result);
        assertEquals("5 - abcd", result);

        /*
         * it is equivalent to the code below
         */
        String switchResult;
        switch (s) {
            case "1":
                switchResult = s + s;
                break;
            case "2":
                switchResult = s + "a";
                break;
            case "3":
            case "4":
            case "5":
            case "6":
                switchResult = s + " - abcd";
                break;
            default:
                switchResult = "no match";
        }

        assertEquals("5 - abcd", switchResult);

    }

    @Test
    public void testPatternNullValue() {
        String s = null;
        String strResult1 = match(s)
                // Avoid "Ambiguous method call", if you want to match `null` value, you need use `(String) null` or in(null)
                .when((String) null, v -> "string null value")
                .when("abcd", v -> "is not null")
                .orElse(v -> "other value");

        assertEquals("string null value", strResult1);

        String strResult2 = match(s)
                .when(in(null), v -> "contains null value")
                .when("abcd", v -> "is not null")
                .orElse(v -> "other value");

        assertEquals("contains null value", strResult2);

        String strResult3 = match(s)
                .when(in("a", "b", null, "c"), v -> "contains null value")
                .when("abcd", v -> "is not null")
                .orElse(v -> "other value");

        assertEquals("contains null value", strResult3);


        String nullStr = null;
        String result = match(nullStr, BOOLEAN)  // specify a BOOLEAN mode
                .when(null, v -> "match string null")
                .when("abc".equals(nullStr), v -> "i less than 1")
                .orElse(v -> "str value: " + nullStr);

        assertEquals("match string null", result);


        Tuple2<String, Integer> t2 = null;
        String classMatch = match(t2, TYPE)
                .when(Integer.class, v -> "integer class")
                .when(null, v -> "value is null: " + v)
                .when(Tuple2.class, v -> "tuple2 class")
                .orElse(v -> "other class");

        assertEquals("value is null: " + null, classMatch);

        String res = match(null, VALUE)
                .when(null, v -> "null value")
                .orElse(v -> "other value");
        assertEquals("null value", res);
    }

    @Test
    public void testPatternValue2() {
        int i = 10;
        Void nullValue = match(i)
                .when(1,
                        /*
                         * if you want to match `when(V matchValue, V1<V> action)` not `when(V matchValue, R1<V, R> action)`,
                         * you need add `{ }`, see: void-compatible and value-compatible,
                         */
                        v -> { System.out.println("match value：" + v); })  // add {} to void-compatible
                .whenNext(10,
                        v -> System.out.println("match value：" + v + " whenNext continue..."))
                .when(20,
                        v -> System.out.println("match value：" + v))
                .orElse(
                        v -> System.out.println("--orElse--"));
        /*
         * output:
         * match value：10 whenNext continue...
         * --orElse--
         */
    }


    @Test
    public void testPatternType() {
        Object o = Tuple.of("zs", 20);

        Integer result = match(o, TYPE)  // add `TYPE` to match Class<?>
                .when(Integer.class, v -> v + 10)
                .when(Tuple2.class,  v -> v.arity())
                .when(String.class,  v -> v.contains("abc") ? 20 : 30)
                .orElse(v -> 40);

        System.out.println(result);
        assertEquals(2, result);

        /*
         * it is equivalent to the code below
         */
        Integer ifResult;
        if (o instanceof Integer) {
            ifResult = (Integer) o + 10;
        } else if (o instanceof Tuple2) {
            ifResult = ((Tuple2) o).arity();
        } else if (o instanceof String) {
            ifResult = ((String) o).contains("abc") ? 20 : 30;
        } else {
            ifResult = 40;
        }

        assertEquals(2, ifResult);
    }

    @Test
    public void testStringValue1() {
        String str = "aBcdE123.$fGHIj";
        // ignore case match
        String matchRes1 = match(str, IGNORECASE)
                .when((String) null, v -> "match null")
                .when("abcd", v -> "match abcd")
                .when("abcde123.$fGHIj", v -> "ignore case match")
                .orElse(v -> "no match");
        assertEquals("ignore case match", matchRes1);
        // CONTAIN match
        String matchRes2 = match(str, CONTAIN)
                .when("abcd", v -> "abcd")
                .when("E123", v -> "E123")
                .orElse(v -> "no match");
        assertEquals("E123", matchRes2);
        // ignore case for contain
        String matchRes3 = match(str, ICCONTAIN)
                .when("abcd1", v -> "abcd1")
                .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")
                .orElse(v -> "no match");
        assertEquals(".$fghi", matchRes3);
        // PREFIX
        String matchRes4 = match(str, PREFIX)
                .when("abcd", v -> "abcd")
                .when("aBcd", v -> "aBcd")
                .orElse(v -> "no match");
        assertEquals("aBcd", matchRes4);
        // ignore case for suffix
        String matchRes5 = match(str, ICSUFFIX)
                .when("fghij", v -> "fGHIj")
                .when("aBcd", v -> "aBcd")
                .orElse(v -> "no match");
        assertEquals("fGHIj", matchRes5);
    }

    @Test
    public void testStringValue2() {
        String str = null;
        // ignore case match
        String matchRes1 = match(str, IGNORECASE)
                .when("abcd", v -> "match abcd")
                .when((String) null, v -> "match null")
                .when("abcde123.$fGHIj", v -> "ignore case match")
                .orElse(v -> "no match");
        assertEquals("match null", matchRes1);
        // CONTAIN match
        String matchRes2 = match(str, CONTAIN)
                .when("abcd", v -> "abcd")
                .when(in(null), v -> "null")
                .orElse(v -> "no match");
        assertEquals("null", matchRes2);
        // ignore case for contain
        String matchRes3 = match(str, ICCONTAIN)
                .when("abcd1", v -> "abcd1")
                .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")
                .orElse(v -> "no match");
        assertEquals(".$fghi", matchRes3);
        // PREFIX
        String matchRes4 = match(str, PREFIX)
                .when("abcd", v -> "abcd")
                .when((PatternIn<String>) null, v -> "PatternIn<String>")
                .orElse(v -> "no match");
        assertEquals("PatternIn<String>", matchRes4);
        // ignore case for suffix
        String matchRes5 = match(str, ICSUFFIX)
                .when(in(null, null, null), v -> "all null")
                .when("aBcd", v -> "aBcd")
                .orElse(v -> "no match");
        assertEquals("all null", matchRes5);
    }

    @Test
    public void testPatternBoolean() {
        int i = 10;
        String result = match(i)
                .when(i == 0,
                        v -> "i is zero")
                .when(i < 5 && i > 0,
                        v -> "i is between 0~5")
                .when(i > 5,
                        v -> "i is greater than 5")
                .orElse(v -> "i is equals to: " + v);
        System.out.println("match result：" + result);
        assertEquals("i is greater than 5", result);

        /*
         * it is equivalent to the code below
         */
        String ifResult;
        if (i == 0) {
            ifResult = "i is zero";
        } else if (i < 5 && i > 0) {
            ifResult = "i is between 0~5";
        } else if (i > 5) {
            ifResult = "i is greater than 5";
        } else {
            ifResult = "i is equals to: " + i;
        }
        assertEquals("i is greater than 5", ifResult);
    }

    @Test
    public void testPatternClassValue() {
        Class<String> strClass = String.class;
        Tuple2<String, Integer> t2 = Tuple.of("1", 1);

        match(t2.getClass())
                .when(Objects.class,
                        v -> { System.out.println("match objects"); })
                .when(String.class,
                        v -> System.out.println("match stringclass"))
                .when(in(Long.class, Tuple2.class, Integer.class),
                        v -> System.out.println("match tuple class: " + v))
                .orElse(v -> System.out.println("not match"));
    }

}
