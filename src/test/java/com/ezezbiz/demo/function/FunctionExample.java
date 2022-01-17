package com.ezezbiz.demo.function;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FunctionExample {
    @Test
    public void testFunctionApply(){

        Integer funAdd = add.apply(20);
        Integer fun2 = multiply.apply(20);

        //Then
        assertThat(funAdd, is(equalTo(incrementByOne(20))));
        assertThat(200, is(equalTo(fun2)));
    }

    @Test
    public void testFunctionAndThen(){
        Function<Integer, Integer> function = add.andThen(multiply);
        Integer result = function.apply(1);

        System.out.println(result);
    }

    @Test
    public void testBiFunction(){
        Integer result = integerBiFunction.apply(10, 20);

        //Then
        assertThat(result, is(220));
    }

    static Function<Integer, Integer> add = x-> x + 1;

    static Function<Integer, Integer>  multiply = x-> x * 10;

    static int incrementByOne(int number){
        return number + 1;
    }

    static BiFunction<Integer, Integer, Integer> integerBiFunction =
            (number, numberToMultiply)->(number+1)*numberToMultiply;

    static int addByOneAndMultiply(int number, int numberToMultiply){
        return (number+1)*numberToMultiply;
    }


}
