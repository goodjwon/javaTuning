package com.ezezbiz.demo.algorithm;

public class BubleSort {
    static int i, j, temp;
    static int array[] = {1, 10, 4, 5, 6, 7, 8, 3, 2, 9};


    public static void main(String[] args) {
        for (i = 0; i < 10; i++) {
            for(j=0; j<9-i; j++){
                if(array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j + 1] = temp;
                }
            }
        }
        for(i = 0; i< 10; i++){
            System.out.println(array[i]);
        }
    }
}
