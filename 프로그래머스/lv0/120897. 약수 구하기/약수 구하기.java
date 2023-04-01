import java.util.*;
class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(n%i==0) list.add(i);
        }
        int[] answer = list.stream().mapToInt(x->x).toArray();
        return answer;
    }
}