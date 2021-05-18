package com.github.gg_a;

import com.github.gg_a.tuple.Tuple;
import com.github.gg_a.tuple.Tuple2;
import org.junit.jupiter.api.Test;
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

}
