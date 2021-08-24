package com.ezezbiz.demo.algorithm;

/**
 * 5.22.2021
 */
public class BubleSort {
    static int array[] = RandomNumbers.getNumbersArray();

    public static void main(String[] args) {
        int result[] = sort(array);

        for(int a : result){
//            System.out.println(a);
        }

    }

    public static int[] sort(int[] a) {
        long start, end;    //시간 탐색
        start = System.currentTimeMillis();
        bubble_sort(a, a.length);
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 +"초 걸림");
        return a;
    }

    private static void bubble_sort(int[] a, int size) {

        int[] result;
        // round는 배열 크기 - 1 만큼 진행됨
        for(int i = 1; i < size; i++) {
            // 각 라운드별 비교횟수는 배열 크기의 현재 라운드를 뺀 만큼 비교함
            for(int j = 0; j < size - i; j++) {
                /*
                 *  현재 원소가 다음 원소보다 클 경우
                 *  서로 원소의 위치를 교환한다.
                 */
                if(a[j] > a [j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
