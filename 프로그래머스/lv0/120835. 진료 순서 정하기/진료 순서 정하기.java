import java.util.*;
class Solution {
    public int[] solution(int[] emergency) {
        Integer[] arr = Arrays.stream(emergency).boxed().toArray(Integer[] ::new);
        Arrays.sort(arr,Collections.reverseOrder()); // 76,24,3
        int[] answer = new int[emergency.length];
        for(int i=0; i<arr.length; i++){
        	// 정렬된 배열에서는 indexOf()동작 불가능, binarySearch()쓰자
            answer[i] = Arrays.binarySearch(arr,emergency[i],Collections.reverseOrder())+1;
        }
        return answer;
    }
}