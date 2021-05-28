package com.ezezbiz.demo.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStreamsMap {
    public static void main(String[] args) {
        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        //toUpperCase
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println(alpha); //[a, b, c, d]
        System.out.println(alphaUpper); //[A, B, C, D]

        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);

        //Staff => StaffPublic

        List<Staff> staffs = Arrays.asList(
                new Staff("jwon", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<StaffPublic> result = staffs.stream().map(temp -> {
            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("jwon".equals(temp.getName())) {
                obj.setExtra("this field is for jwon only!");
            }
            return obj;
        }).collect(Collectors.toList());

        System.out.println(result);
        /*
            [
                StaffPublic{name='jwon', age=30, extra='this field is for jwon only!'},
                StaffPublic{name='jack', age=27, extra='null'},
                StaffPublic{name='lawrence', age=33, extra='null'}
            ]
         */


    }
}


class Staff {
    private String name;
    private int age;
    private BigDecimal salary;

    public Staff(String name, int age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}


class StaffPublic {
    private String name;
    private int age;
    private String extra;

    public StaffPublic() {
    }

    public StaffPublic(String name, int age, String extra) {
        this.name = name;
        this.age = age;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getExtra() {
        return extra;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "StaffPublic{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", extra='" + extra + '\'' +
                '}';
    }
}