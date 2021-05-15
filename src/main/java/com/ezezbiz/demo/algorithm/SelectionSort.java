package com.ezezbiz.demo.algorithm;

public class SelectionSort {
    /**
     *      5, 10, 1, 7, 8, 6, 4, 2, 3, 9
     *      1, 10, 5, 7, 8, 6, 4, 2, 3, 9
     *      1, 2, 5, 7, 8, 6, 4, 10, 3, 9
     *      1, 2, 3, 7, 8, 6, 4, 10, 5, 9
     *      1, 2, 3, 4, 8, 6, 7, 10, 5, 9
     *      1, 2, 3, 4, 5, 6, 7, 10, 8, 9 ...
     *      선택후 가장 작은 수를 앞에 놓고 계속 해서 반복한다.
     *      등차수열 총 55회 계산.
     *      10 * (10+1)/2 = 55;
     */
    private static int[] sort(){

        int[] data = RandomNumbers.getNumbersArray();

        int i, j;   // i, j 원소 탐색
        int min;    // min 최소값
        int index = 0;  // index 가장작은 값의 위치
        int temp;   //temp 값 스위치를 위한 임시 저장소
        for(i = 0; i < data.length; i++){
            min = Integer.MAX_VALUE;
            for (j = i; j < data.length; j++){
                if(min > data[j]){
                    min = data[j];
                    index = j;
                }
            }
            //swapping patten
            temp = data[i];
            data[i] = data[index];
            data[index] = temp;
        }
        System.out.println(data);
        return data;
    }

    public static void main(String[] args) {
        long start, end;    //시간 탐색
        start = System.currentTimeMillis();
        int results[] = sort();
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 + "초 걸림.");

//        for (int a:results){
//            System.out.println(a);
//        }
    }
}
