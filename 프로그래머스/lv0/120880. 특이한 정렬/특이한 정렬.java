import java.util.*;
class Solution {
    public int[] solution(int[] numlist, int n) {
        Arrays.sort(numlist);
        List<Integer> nlist = new ArrayList<>();
        for (int num : numlist) {
            nlist.add(num);
        }
        int[] answer = new int[numlist.length];
        
        //배열 돌면서 차의 절댓값 가장 작은것 넣기
        int min = Integer.MAX_VALUE, tmp=0;
        
        for(int i=0; i<answer.length; i++){
            for(int j=0; j<nlist.size(); j++){
                if(min >= Math.abs(n-nlist.get(j))) {
                    min = Math.abs(n-nlist.get(j));
                    tmp = nlist.get(j);
                }
            }
            answer[i] = tmp;
            nlist.remove(Integer.valueOf(tmp));
            min = Integer.MAX_VALUE;
        }
        
        return answer;
    }
}