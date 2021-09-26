package com.ezezbiz.demo.types;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTypeTest {
    public static void main(String[] args) {
        String dateType = "20210501";
        String dateTimeType = "20210501201950";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDate date = LocalDate.parse(dateType, formatter);
        LocalDateTime date2 = LocalDateTime.parse(dateTimeType, formatter2);

        System.out.println(date);
        System.out.println(date2);
    }
}
