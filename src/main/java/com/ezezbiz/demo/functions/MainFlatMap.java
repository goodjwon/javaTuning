package com.ezezbiz.demo.functions;

import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
public class MainFlatMap {


    public static void main(String[] args) {

        List<Job> collect = getPeople().stream()
                .map(person -> getJobByPerson(person))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }


    private static List<Job> getJobByPerson(Person person) {
        return getJobs().stream()
                .filter(job -> job.getCompany().equals(person.getCompanyCode()))
                .collect(Collectors.toList());
    }



    private static List<Job> getJobs(){
        return List.of(
                Job.builder().company("1").jobName("Dev").salary(10000).build(),
                Job.builder().company("2").jobName("Design").salary(20000).build());
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20,  Gender.MALE, "1"),
                new Person("Alina Smith", 33, Gender.FEMALE, "2"),
                new Person("Helen White", 57, Gender.FEMALE, "1"),
                new Person("Alex Boz", 14, Gender.MALE, "2"),
                new Person("Jamie Goa", 99, Gender.MALE, "2"),
                new Person("Anna Cook", 7, Gender.FEMALE, "1"),
                new Person("Zelda Brown", 120, Gender.FEMALE, "1")
        );
    }
}
