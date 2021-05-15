package com.ezezbiz.demo.algorithm;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomNumbers {
        public static int[] getNumbersArray(){
            return new Random().ints(0,500000).limit(500000).toArray();
        }

        public static List<Integer> getNumbersList(){
            return new Random().ints(0,500000).limit(500000).boxed().collect(Collectors.toList());
        }
}
