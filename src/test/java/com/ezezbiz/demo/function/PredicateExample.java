package com.ezezbiz.demo.function;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PredicateExample {

    @Test
    public void testCheckPhoneNumber() {
        assertThat(isPhoneNumberValid("07000000000"), is(true));
        assertThat(isPhoneNumberValid("09000000000"), is(false));
        assertThat(isPhoneNumberValidPredicate.test("07000000000"), is(true));
        assertThat(isPhoneNumberValidPredicate.test("09000000000"), is(false));

        assertThat(containsNumber3.test("3700000000"), is(true));
        assertThat(isPhoneNumberValidPredicate.and(containsNumber3).test("37000000000"), is(false));
        assertThat(isPhoneNumberValidPredicate.or(containsNumber3).test("37000000000"), is(true));
        assertThat(isPhoneNumberValidPredicate.and(containsNumber3).test("07000000003"), is(true));
    }

    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }

    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber
            -> phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    static Predicate<String> containsNumber3 = phoneNumber
            -> phoneNumber.contains("3");
}
