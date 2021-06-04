package com.ezezbiz.demo.algorithm;

public class Recurision_1 {
    public static void main(String[] args) {
        int n = 4;
        func(n);
    }

    public static void func(int k) {
        if (k<=0) return;
        else {
            func(k-1);
            System.out.println("Hello Recursion !!!"+k);
        }
    }
}
