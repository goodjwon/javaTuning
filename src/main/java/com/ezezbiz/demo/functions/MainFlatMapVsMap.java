package com.ezezbiz.demo.functions;

import java.util.Arrays;

public class MainFlatMapVsMap {
    public static void main(String[] args) {


        String[][] namesArray = new String[][]{
                {"kim", "taeng"}, {"mad", "play"}};

        //map
        System.out.println("=====map====All");
        Arrays.stream(namesArray)
                .map(Arrays::stream)
                .forEach(System.out::println);

        System.out.println("=====map====1");
        Arrays.stream(namesArray)
                .map(Arrays::stream)
                .forEach(names -> names.filter(name -> name.contains("taeng"))
                        .forEach(System.out::println));


        //flatMap
        System.out.println("=====flatMap====All");
        Arrays.stream(namesArray)
                .flatMap(Arrays::stream)
                .forEach(System.out::println);

        System.out.println("=====flatMap====1");
        Arrays.stream(namesArray)
                .flatMap(inner -> Arrays.stream(inner))
                .filter(name -> name.equals("taeng"))
                .forEach(System.out::println);
    }
}
