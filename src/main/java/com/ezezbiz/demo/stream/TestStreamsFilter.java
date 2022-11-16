package com.ezezbiz.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStreamsFilter {
    public static void main(String[] args) {

        // use string
        List<String> lines = Arrays.asList("spring", "node", "jwon");

        //before
        List<String> result = getFilterOutput(lines, "jwon");
        System.out.println("before java8");
        for (String temp : result) {
            System.out.println(temp);    //output : spring, node
        }

        //after
        result = lines.stream()                // convert list to stream
                .filter(line -> !"jwon".equals(line))     // we dont like jwon
                .collect(Collectors.toList());              // collect the output and convert streams to a List
        System.out.println("after java8");
        result.forEach(System.out::println);


        result = lines.stream()
                .filter(line -> !"jwon".equals(line))
                .collect(Collectors.toList());
        result.forEach(System.out::println);

        //use object
        List<Person> persons = Arrays.asList(
                new Person("jwon", 42),
                new Person("jwon1", 40),
                new Person("jwon2", 40));


        Person result1 = persons.stream()
                .filter(person -> "jwon".equals(person.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result1);

        Person result2 = persons.stream()
                .filter(person -> 40 == person.getAge())    //처음걸리는 것만 반환한다.
                .findAny()
                .orElse(null);
        System.out.println(result2);

        Person result3 = persons.stream()
                .filter(person -> "jwon".equals(person.getName()) && 42 == person.getAge())
                .findAny()
                .orElse(null);

        System.out.println(result3);

        //use map 단수 처리
        String name = persons.stream()
                .filter(person -> "jwon".equals(person.getName()))
                .map(Person::getName)
                .findAny()
                .orElse(null);
        System.out.println(name);

        // use map 복수처리
        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

    //before
    private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (!"jwon".equals(line)) { // we dont like jwon
                result.add(line);
            }
        }
        return result;
    }
}

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //gettersm setters, toString

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
