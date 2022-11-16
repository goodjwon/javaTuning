package com.ezezbiz.demo.algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultSort {
    public static void main(String[] args) { // 0.009 Dual-Pivot Quick
        int[] arr = RandomNumbers.getNumbersArray();
//        System.out.println("before sort");

//        Arrays.stream(arr).forEach(a ->{
//            System.out.println(a);
//        });

//        System.out.println("after sort");
        long start, end;    //시간 탐색
        start = System.currentTimeMillis();
        List<Integer> result = Arrays.stream(arr)
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 + "초 걸림.");


//        Arrays.stream(arr).sorted().forEach(a ->{
//            System.out.println(a);
//        });
    }
}


