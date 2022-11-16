package com.ezezbiz.demo.function;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalExample {
    @Test
    public void testOptional(){
        Optional.ofNullable("null").orElseThrow(()-> new IllegalArgumentException("exception"));
    }

    @Test
    public void testOptionalIfPresent(){
        Optional.ofNullable("goodjwon@gmail.com").ifPresent(
                email-> System.out.println("Sending email to "+ email));

        Optional.ofNullable(null).ifPresentOrElse(
                email-> System.out.println("Sending email to"+ email),
                ()-> System.out.println("Cannot send email"));
    }

}
