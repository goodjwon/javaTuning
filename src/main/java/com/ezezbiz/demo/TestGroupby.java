package com.ezezbiz.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestGroupby {

    public static void main(String[] args) {
        //3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result;

        result = items.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
        ));

        System.out.println(result);

        Map<String, List<String>> resultString = items.stream().collect(Collectors.groupingBy(Function.identity()));

        System.out.println(resultString);

        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);

        //3 apple, 2 banana, others 1
        List<Item> iteminfo = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99")));

        Map<String, Long> counting = iteminfo.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);

        Map<String, Integer> sum = iteminfo.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);

    }
}


class Item{

    private String name;
    private int qty;
    private BigDecimal price;

    public Item() {
    }

    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public BigDecimal getPrice() {
        return price;
    }
}