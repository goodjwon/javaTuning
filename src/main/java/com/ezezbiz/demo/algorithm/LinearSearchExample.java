package com.ezezbiz.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LinearSearchExample {
    public static void main(String[] args) {
        // 배열 초기화 (1,000,000개의 숫자를 임의로 생성)
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100); // 0~99 범위의 랜덤 값
        }

        // 찾고자 하는 값
        int target = 42;

        // 선형 탐색 수행
        List<Integer> result = findIndices(array, target);

        // 결과 출력
        if (result.isEmpty()) {
            System.out.println("찾고자 하는 값이 배열에 없습니다.");
        } else {
            System.out.println("찾고자 하는 값 " + target + "의 인덱스: " + result);
        }
    }

    // 선형 탐색 메서드
    public static List<Integer> findIndices(int[] array, int target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                indices.add(i); // 원하는 값을 찾으면 인덱스를 저장
            }
        }
        return indices;
    }
}
