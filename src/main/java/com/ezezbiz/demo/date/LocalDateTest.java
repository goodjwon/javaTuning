package com.ezezbiz.demo.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    public static void main(String args[]) {
        if( args.length > 0){
            String string = args[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(string, formatter);
            System.out.println(date.format(formatter));                 //입력일자
            System.out.println(date.plusDays(5).format(formatter));     //5 일 후
        } else {
            System.out.println("args is 0");
        }

    }
}