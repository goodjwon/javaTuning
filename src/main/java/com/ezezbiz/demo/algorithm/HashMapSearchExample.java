package com.ezezbiz.demo.algorithm;

import java.util.*;

public class HashMapSearchExample {
    public static void main(String[] args) {
        // 배열 초기화
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100); // 0~99 범위의 랜덤 값
        }

        // 찾고자 하는 값
        int target = 42;

        // 해시맵 생성
        Map<Integer, List<Integer>> indexMap = buildIndexMap(array);

        // 결과 조회
        List<Integer> result = indexMap.getOrDefault(target, Collections.emptyList());

        // 결과 출력
        if (result.isEmpty()) {
            System.out.println("찾고자 하는 값이 배열에 없습니다.");
        } else {
            System.out.println("찾고자 하는 값 " + target + "의 인덱스: " + result);
        }
    }

    // 배열 값을 기반으로 해시맵 생성
    public static Map<Integer, List<Integer>> buildIndexMap(int[] array) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            indexMap.computeIfAbsent(array[i], k -> new ArrayList<>()).add(i);
        }
        return indexMap;
    }
}
