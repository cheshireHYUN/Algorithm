// 서로 다른 옷의 조합의 수를 리턴하라.
// 이차원배열로 카테고리가 주어지니까 각 카테고리의 갯수를 계산
// 예를들어 헤드기어:2개, 아이웨어:1개면 
// (헤드기어x, 헤드기어1, 헤드기어2) * (아이웨어X, 아이웨어1) = 6 - 1(둘다xx) = 5
// 즉, 각 VALUE+1을 누적해서 곱한뒤 1을 빼면된다.

import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for(String[] clothe : clothes) {
            String key = clothe[1];
            map.put(key, map.getOrDefault(key,0)+1);
        }
        
        int answer = 1;
        for(String key : map.keySet()) {
            answer *= (map.get(key)+1);
        }
        
        return answer-1;
    }
}