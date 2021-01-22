package com.ezezbiz.demo.functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        System.out.println("===java normal method===");
        System.out.println(isPhomeNumberValid("07222222222"));
        System.out.println(isPhomeNumberValid("0722222222"));
        System.out.println(isPhomeNumberValid("09222222222"));

        System.out.println("===java function===");
        System.out.println(isPhomeNumberValidPredicate.test("07222222222"));
        System.out.println(isPhomeNumberValidPredicate.test("0722222222"));
        System.out.println(isPhomeNumberValidPredicate.test("09222222222"));

        System.out.println("===java function multi check===");
        System.out.println(isPhomeNumberValidPredicate.and(containsNumber3).test("07222222223"));
        System.out.println(isPhomeNumberValidPredicate.and(containsNumber3).test("07222222222"));
        System.out.println(isPhomeNumberValidPredicate.and(containsNumber3).test("072222222"));

    }

    static boolean isPhomeNumberValid(String phoneNumber){
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }

    static Predicate<String> isPhomeNumberValidPredicate = phoneNumber ->
            phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    static Predicate<String> containsNumber3 = phoneNumber ->
            phoneNumber.contains("3");
}
