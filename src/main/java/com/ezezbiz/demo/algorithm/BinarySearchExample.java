package com.ezezbiz.demo.algorithm;

import java.util.*;

public class BinarySearchExample {
    public static void main(String[] args) {
        // 배열 초기화
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100); // 0~99 범위의 랜덤 값
        }

        // 찾고자 하는 값
        int target = 42;

        // 배열 정렬
        Arrays.sort(array);

        // 이진 탐색으로 범위 찾기
        int firstIndex = findFirstIndex(array, target);
        int lastIndex = findLastIndex(array, target);

        // 결과 출력
        if (firstIndex == -1) {
            System.out.println("찾고자 하는 값이 배열에 없습니다.");
        } else {
            System.out.println("찾고자 하는 값 " + target + "의 인덱스 범위: ");
            for (int i = firstIndex; i <= lastIndex; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 처음 나타나는 위치 찾기
    public static int findFirstIndex(int[] array, int target) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                result = mid;
                high = mid - 1; // 더 왼쪽으로 탐색
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 마지막 나타나는 위치 찾기
    public static int findLastIndex(int[] array, int target) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                result = mid;
                low = mid + 1; // 더 오른쪽으로 탐색
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}
