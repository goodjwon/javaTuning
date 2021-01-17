package com.ezezbiz.demo.functions;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();
        //1. 전통적인 루프 방식
        System.out.println("1. ============================================");
        List<Person> females = new ArrayList<>();
        for (Person person:people){
            if(person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        females.forEach(System.out::println);

        System.out.println("2. ============================================");

        //2. Use filter
        List<Person> femalesFilter = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        femalesFilter.forEach(System.out::println);

        System.out.println("3~6. ============================================");
        //3. Use sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //4. All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 6);
        System.out.println(allMatch);

        //5. Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 121);
        System.out.println(anyMatch);

        //6. None match
        boolean noneMatch =people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
        System.out.println(noneMatch);

        //7. Max
        people.stream().max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //8. Min
        people.stream().min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //9. Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        }));

        //10. 응용
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
            new Person("Antonio", 20, Gender.MALE),
            new Person("Alina Smith", 33, Gender.FEMALE),
            new Person("Helen White", 57, Gender.FEMALE),
            new Person("Alex Boz", 14, Gender.MALE),
            new Person("Jamie Goa", 99, Gender.MALE),
            new Person("Anna Cook", 7, Gender.FEMALE),
            new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
