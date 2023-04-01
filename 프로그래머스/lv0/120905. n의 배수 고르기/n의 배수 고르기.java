import java.util.*;
class Solution {
    public int[] solution(int n, int[] numlist) {
        //numlist에서 n의 배수만 남긴다
        List<Integer> list = new ArrayList<>();
        for(int i : numlist){
            if(i%n==0) list.add(i);
        }
        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}