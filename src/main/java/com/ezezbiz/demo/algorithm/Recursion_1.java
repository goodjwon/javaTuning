package com.ezezbiz.demo.algorithm;


/**
 * func(int n)은 음이 아닌 정수 n에 대해서 0에서 n까지의 합을 올바로 계산한다.
 * 1. n = 0 인경우 0을 반환한다.
 * 2. 임의의 양의정수 k에 대해서 n < k인 경우 n까지의 합을 올바르게 계산한다고 가정
 * 3. n=k인 경우 func은 func(k-1) 호출 하는데 2번의 가정에 의해서 0에서 k-1까지의 합이 올바로 계산되어 반환된다.
 */
public class Recursion_1 {
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
