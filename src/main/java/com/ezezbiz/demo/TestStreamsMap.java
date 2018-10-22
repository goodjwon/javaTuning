package com.ezezbiz.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStreamsMap {
    public static void main(String[] args) {
        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        //toUpperCase
        List<String> alphaUpper = new ArrayList<>();
        for(String s:alpha){
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println(alpha); //[a, b, c, d]
        System.out.println(alphaUpper); //[A, B, C, D]

    }
}
