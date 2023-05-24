package com.ezezbiz.demo.function;

import com.ezezbiz.demo.functions.Gender;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    @Test
    public void testStream() {
        List<Person> people = List.of(
                new Person("jwon1", Gender.MALE),
                new Person("jwon22", Gender.MALE),
                new Person("jwon333", Gender.FEMALE),
                new Person("jwon4444", Gender.MALE),
                new Person("jwon55555", Gender.FEMALE),
                new Person("jwon6666666", Gender.PREFER_NOT_TO_SAY)
        );

        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length)
                .forEach(System.out::println);

        people.stream()
                .map(person -> person.name)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        boolean match = people.stream().allMatch(person -> Gender.FEMALE.equals(person.gender));

        System.out.println(match);


    }

    static class Person{
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }


    }
}
