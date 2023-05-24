package com.ezezbiz.demo.streams.examples;

import com.ezezbiz.demo.streams.beans.Car;
import com.ezezbiz.demo.streams.beans.Person;
import com.ezezbiz.demo.streams.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class Sorting {

    @Test
    public void testSortingFirstName() throws IOException {
        List<Person> people = MockData.getPeople();
        List<String> sorted = people.stream()
                .map(Person::getFirstName)
                .sorted()
                .collect(Collectors.toList());
//        sorted.forEach(s-> System.out.println(s));

        assertThat(sorted.get(0), is(startsWith("A")));
    }

    @Test
    public void testSortingFirstNameDesc() throws IOException{
        List<Person> people = MockData.getPeople();
        List<String> sorted = people.stream().map(Person::getFirstName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        assertThat(sorted.get(0), is(startsWith("Z")));
        sorted.forEach(s-> System.out.println(s));
    }

    @Test
    public void testSortingFirstNameByComparator() throws IOException{
        List<Person> people = MockData.getPeople();
        Comparator<Person> comparing = Comparator
                .comparing(Person::getEmail)
                .reversed()
                .thenComparing(Person::getFirstName);

        List<Person> sort = people.stream()
                .sorted(comparing)
                .collect(Collectors.toList());
        sort.forEach(System.out::println);
    }

    @Test
    public void testSortingTopTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> sorted = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);
    }

}
