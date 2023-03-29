import java.util.*;
class Solution {
    public List<Integer> solution(int[] num_list) {
        List<Integer> answer = new ArrayList<>();
        for(int i=num_list.length-1; i>=0; i--){
            answer.add(num_list[i]);
        }
        return answer;
    }
}