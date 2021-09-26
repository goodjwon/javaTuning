package com.ezezbiz.demo.algorithm;

import java.util.Arrays;

public class QuickSort {

    public static void QuickSort(int[] array) {
        QuickSort(array, 0, array.length - 1);
    }
    public static void QuickSort(int[] array, int left, int right){
        if(left < right) {
            int index = partition(array, left, right);
            QuickSort(array, left, index - 1);
            QuickSort(array, index + 1, right);
        }
    }
    public static int partition(int[] array, int left, int right){
        int pivot = array[right];
        int first = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if(array[j] < pivot) {
                first ++;
                swap(array, first, j);
            }
        }
        swap(array, first + 1, right);
        return first + 1;
    }
    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    public static void main(String[] args)
    {
        int[] myArray = RandomNumbers.getNumbersArray();

        long start, end;    //시간 탐색
        start = System.currentTimeMillis();
//        System.out.println(Arrays.toString(myArray));
        QuickSort(myArray);
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 +"초 걸림");
//        System.out.println(Arrays.toString(myArray));
    }


}
