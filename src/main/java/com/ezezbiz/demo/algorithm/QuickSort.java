package com.ezezbiz.demo.algorithm;

public class QuickSort {
    private static int data[] = {1, 10, 5, 7, 8, 6, 4, 2, 3, 9};

    public static void quickSort(int[] data, int start, int end){
        if(start >= end) {
            System.out.println("end");
            return;
        }
        int pivot = start;
        int i = start + 1;
        int j = end;
        int temp;

        while (i<=j){
            while(i <= end && data[i] < data[pivot]){
                i++;
                System.out.println(i);
            }
            while (j > start && data[j] >= data[pivot]){
                j--;
            }
            if(i > j){
                temp = data[j];
                data[j] = data[pivot];
                data[pivot] = temp;
            } else {
                temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
            quickSort(data, start, j-1);
            quickSort(data, j+1, end);
        }
    }

    public static void show(){
        for(int i = 0; i< data.length; i++){
            System.out.println(data[i] + " ");
        }
    }

    public static void main(String[] args) {
        quickSort(data, 0, data.length-1);
        show();
    }

}
