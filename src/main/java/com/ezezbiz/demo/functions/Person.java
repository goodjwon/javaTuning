package com.ezezbiz.demo.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Builder
@ToString
@Getter
public class Person {
    private final String name;
    private final int age;
    private final Gender gender;

    private final String companyCode;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
