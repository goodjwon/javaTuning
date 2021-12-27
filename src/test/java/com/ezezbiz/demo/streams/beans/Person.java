package com.ezezbiz.demo.streams.beans;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@ToString
public class Person {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final Integer age;
}
