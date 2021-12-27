package com.ezezbiz.demo.streams.examples;

import com.ezezbiz.demo.streams.beans.Car;
import com.ezezbiz.demo.streams.mockdata.MockData;
import org.junit.Test;

import java.util.List;

public class Filtering {

    @Test
    public void filter() throws Exception{
        List<Car> cars = MockData.getCars();

        cars.forEach(System.out::println);
    }
}
