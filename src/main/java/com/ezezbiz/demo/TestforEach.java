package com.ezezbiz.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestforEach {
    public static void main(String[] args) {

        Map <String, Integer> mapItems = getMapItems();

        //before
        for (Map.Entry<String, Integer> entry : mapItems.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }
        System.out.println("\n\nJava8 forEach and Map \n");
        //java8
        mapItems.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));


        List<String> arrayItems = getListItems();

        for(String item : arrayItems){
            System.out.println(item);
        }

        System.out.println("\n\nJava8 forEach and Array \n");
        arrayItems.forEach((k)-> System.out.println(k));


        //Output : C
        System.out.println("\nforEach and Array use if\n");
        arrayItems.forEach((itme)->{
            if("C".equals(itme)){
                System.out.println(itme);
            }
        });

        //method reference
        //Output : A,B,C,D,E
        System.out.println("\nforEach and Array use reference\n");
        arrayItems.forEach(System.out::println);

        //Stream and filter
        //Output : B
        System.out.println("\nforEach and Array use stream\n");
        arrayItems.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);
    }

    public static Map getMapItems(){
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        return items;
    }

    public static List getListItems(){
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");


        return items;

    }
}
