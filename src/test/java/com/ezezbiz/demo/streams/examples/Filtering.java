package com.ezezbiz.demo.streams.examples;

import com.ezezbiz.demo.streams.beans.Car;
import com.ezezbiz.demo.streams.beans.Person;
import com.ezezbiz.demo.streams.mockdata.MockData;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasItem;

public class Filtering {

    @Test
    @DisplayName("filter 테스트. 가격 99,000 이상, 노란차만 가져오기")
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();

        List<Car> result = cars.stream()
                .filter(car -> car.getPrice() > 10000)
                .filter(car->car.getColor().equals("Yellow"))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    @Test
    @DisplayName("filter 테스트. Predicate 를 활용하여 가격 99,000 이상, 노란차만 가져오기")
    public void filterUsePredicate() throws Exception {
        List<Car> cars = MockData.getCars();

        Predicate<Car> carPredicate = car -> car.getPrice() > 99000;
        Predicate<Car> yellow = car -> car.getColor().equals("Yellow");

        List<Car> result2 = cars.stream()
                .filter(carPredicate)
                .filter(yellow)
                .collect(Collectors.toList());

        result2.forEach(System.out::println);
    }

    @Test
    @DisplayName("Max 함수를 통해서 가장 큰 값을 가지고 온다.")
    public void getMaxAge() throws Exception{
        List<Person> people = MockData.getPeople();

        people.stream().max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
    }

    @Test
    @DisplayName("min 함수를 통해서 가장 작은 값을 가지고 온다.")
    public void getMinAge() throws Exception {
        List<Person> people = MockData.getPeople();

        people.stream().min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
    }

    @Test
    @DisplayName("group 을 처리 한다.")
    public void getGender() throws Exception {
        List<Person> people = MockData.getPeople();

        Map<String, List<Person>> groupByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1)->{
            System.out.println(gender);
            people1.forEach(System.out::println);
        });
    }

    @Test
    @DisplayName("파란색 색상을 구해서 모델을 출력하세요.")
    public void testGetModel() throws IOException {
        List<Car> cars = MockData.getCars();
        List<String> blueCars = cars.stream()
                .filter(car -> car.getColor().equals("Blue"))
                .map(car->car.getModel())
                .collect(Collectors.toList());

        blueCars.forEach(car -> System.out.println(car));

        MatcherAssert.assertThat(blueCars, hasItem("MDX"));
    }

    @Test
    public void 가격이10만원이상만_구하시오() throws IOException{
        List<Car> cars = MockData.getCars();
        List<Car> cars50000 = cars.stream().filter(car -> car.getPrice() > 99000).collect(Collectors.toList());

        cars50000.forEach(car -> System.out.println(car));
    }

    @Test
    public void 매이커가_토요타이고_출시년도가_2000년_이상인_자동차를_구하시오() throws IOException{
        List<Car> cars = MockData.getCars();
        List<Car> car1 = cars.stream()
                .filter(car -> car.getMake().equals("Toyota"))
                .filter(car -> car.getYear() > 2000)
                .collect(Collectors.toList());

        car1.forEach(car-> System.out.println(car));
    }

    @Test
    public void 매이커가_현대이고_금액이_10000_이상인_자동차를_구하시오() throws IOException{
        List<Car> cars = MockData.getCars();
        List<Car> car1 = cars.stream()
                .filter(car -> car.getMake().equals("Hyundai"))
                .filter(car -> car.getPrice() > 10000)
                .collect(Collectors.toList());

        car1.forEach(car-> System.out.println(car));
    }

    @Test
    public void takeWhile(){
        System.out.println("using filter");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.println(n + " "));
        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .takeWhile(n-> n % 2 ==0)
                .forEach(n -> System.out.println(n + " "));

    }

    @Test
    public void dropWhile() {
        System.out.println("using filter");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.println(n + " "));
        System.out.println();
        System.out.println("using drop while");
        Stream.of(2, 4, 5, 6, 8, 9, 10, 12)
                .dropWhile(n-> n % 2 ==0)
                .forEach(n -> System.out.println(n + " "));
    }

    @Test
    @DisplayName("stream 에 첫 번째 값을 리턴 한다.")
    public void findFirst() {
        int[] numbers = {1,2,3,4,5,6,7,8,10};
        int result = Arrays.stream(numbers).filter(n-> n == 4)
                .findFirst()
                .orElse(-1);

        System.out.println(result);
    }

    @Test
    @DisplayName("stream 에 첫 번째 값을 리턴 한다. 병렬로 진행할 경우 다른 값이 나올 수 있다.")
    public void findAny() {
        int[] numbers = {1,2,3,4,5,6,7,8,10};
        int result = Arrays.stream(numbers).filter(n-> n > 4)
                .findAny()
                .orElse(-1);

        System.out.println(result);
    }

    @Test
    @DisplayName("stream 에 첫 번째 값을 리턴 한다. 병렬로 진행")
    public void findAnyParallel() {
        List<String> elements = Arrays.asList("a", "a1", "b", "b1", "c", "c1");
        Optional<String> anyElement = elements.stream().parallel().filter(s -> s.startsWith("b")).findAny();

        System.out.println(anyElement.get());
    }

    @Test
    @DisplayName("모든 요소가 조건에 만족하는지 검사.")
    public void allMatch() {
        int[] numbers = {2, 4, 6, 8, 10};
        boolean result = Arrays.stream(numbers).allMatch(n-> n % 2 == 0);

        System.out.println(result);
    }
}
