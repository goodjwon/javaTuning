package com.ezezbiz.demo.algorithm;

public class InsertionSort {
    static int array[] = RandomNumbers.getNumbersArray();
//    static int array[] = {1, 10, 5, 6, 7, 8, 4, 3, 2, 9};

    public static void main(String[] args) {
        int result[] = sort(array);

        for(int a : result){
           // System.out.println(a);
        }
    }

    public static int[] sort(int[] a) {
        long start, end;    //시간 탐색
        start = System.currentTimeMillis();
        insertionSort(a);
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 +"초 걸림");

        return a;
    }

    public static void insertionSort(int[] arr){
        for (int end = 1; end <arr.length; end++){
            int i = end;  // 정렬이 되어 있다고 가정 하고 시작.
            while (i> 0 && arr[i -1] > arr[i]){
                swap(arr, i-1, i);
                i--;
            }
        }
    }

    private static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
