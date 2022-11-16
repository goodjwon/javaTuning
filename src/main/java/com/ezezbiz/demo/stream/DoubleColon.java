package com.ezezbiz.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DoubleColon {

    static void someFunction(String s){
        System.out.println(s);
    }

    /**
     * map은 요소들을 특정조건에 해당하는 값으로 변환해 줍니다. 요소들을 대,소문자 변형 등 의 작업을 하고 싶을떄 사용 가능 합니다.
     * filter는 요소들을 조건에 따라 걸러내는 작업을 해줍니다. 길이의 제한, 특정문자포함 등 의 작업을 하고 싶을때 사용 가능합니다.
     * sorted는 요소들을 정렬해주는 작업을 해줍니다.
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("Geeks");
        list.add("Forle");
        list.add("GEEK");

        //표현 1
        list.forEach(a->DoubleColon.someFunction(a));
        System.out.println("<--------------------->");
        //표현 2
        list.forEach(DoubleColon::someFunction);
        System.out.println("<--------------------->");
        //표현 3
        list.stream().map(s->s.toUpperCase()).forEach(s-> System.out.println(s));
        System.out.println("<--------------------->");
        //표현 4
        list.stream().map(String::toUpperCase).sorted().forEach(System.out::println);
    }
}
