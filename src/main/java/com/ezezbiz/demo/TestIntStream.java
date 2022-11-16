package com.ezezbiz.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestIntStream  {

    public static void main(String[] args) {
        testIntStream();
    }

    public static void testIntStream(){
        ArrayList<Integer> collect = IntStream.of(1, 2, 3).collect(ArrayList::new, List::add, List::addAll);
        collect.forEach(System.out::println);
    }


}
