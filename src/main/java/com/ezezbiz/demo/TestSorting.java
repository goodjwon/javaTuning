package com.ezezbiz.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 출처 : http://www.mkyong.com/java8/java-8-lambda-comparator-example/
 */

public class TestSorting {

    public static void main(String[] args) {

        List<Developer> developers = getDevelopers();

        System.out.println("before start");
        for(Developer dev:developers){
            System.out.println(dev);
        }

        //sort by age
        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });


        System.out.println("\nAfter By age Sort");
        for (Developer developer : developers) {
            System.out.println(developer);
        }

        //userLambda
        System.out.println("\nLambda age Sort");
        developers.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
        developers.forEach((developer -> System.out.println(developer)));


        //sort by name
        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println("\nAfter By Name Sort");
        for (Developer developer : developers) {
            System.out.println(developer);
        }

        //sort by salary
        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });

        System.out.println("\nAfter By Salary Sort");
        for (Developer developer : developers) {
            System.out.println(developer);
        }
    }

    public static List<Developer> getDevelopers(){

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;
    }
}
