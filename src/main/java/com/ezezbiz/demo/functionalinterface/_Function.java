package com.ezezbiz.demo.functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 */
public class _Function {
    public static void main(String[] args) {
        int result = increment(1);
        System.out.println(result);

        int result2 = incrementByOneFunction.apply(1);
        System.out.println(result2);

        int mulitiply = multiplayBy10.apply(5);
        System.out.println(mulitiply);

        //함수 넘기기.
        Function<Integer, Integer> addBy1AndThenMultiplayBy10 =
                incrementByOneFunction.andThen(multiplayBy10);

        System.out.println(addBy1AndThenMultiplayBy10.apply(4));

        //Method
        System.out.println(incrementByOneAndMultiplay(4, 100));

        //Function
        System.out.println(incrementByOneAndMultiplayBiFunction.apply(4, 100));
    }

    static Function<Integer, Integer> multiplayBy10 =
            number -> number * 10;

    //static int increment 과 같음
    static Function<Integer, Integer> incrementByOneFunction =
            number -> ++number;


    static int increment(int number) {
        return number + 1;
    }

    // static int incrementByOneAndMultiplay 과 같음
    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplayBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy)
                    -> (numberToIncrementByOne + 1) * (numberToMultiplyBy);

    static int incrementByOneAndMultiplay(int number, int numToMultiplyBy) {
        return (number + 1) * numToMultiplyBy;
    }
}
