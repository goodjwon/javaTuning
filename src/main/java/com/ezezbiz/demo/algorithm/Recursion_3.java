package com.ezezbiz.demo.algorithm;

/**
 * 재귀함수를 사용하여 1 부터 n까지 합을 구하는 프로그램을 작성하라.
 *
 */
public class Recursion_3 {

    public static void main(String[] args) {
        int result = sum(5);
        System.out.println(result);
        result = factorial(5);
        System.out.println(result);
        result = fibonacci (10);
        System.out.println(result);
    }

    static int sum(int n){
        if(n==0){
            return 0;
        } else {
            return n + sum(n-1);
        }
    }

    static int factorial(int n){
        if(n == 1){
            return 1;
        }else{
            return n * factorial(n-1);
        }
    }

    static int fibonacci (int n){
        if(n <= 1){
            return n;
        } else {
            return fibonacci (n-2)+fibonacci (n-1);
        }
    }
}
