package com.ezezbiz.demo.streams.examples;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinMax {

    @Test
    public void testMin(){
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8);
        Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
        assertThat(min, is(1));
    }
}
