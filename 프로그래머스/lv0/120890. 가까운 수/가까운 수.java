import java.util.*;
class Solution {
    public int solution(int[] array, int n) {
        int answer=Integer.MAX_VALUE, len=Integer.MAX_VALUE;
        for(int i : array){
            if(Math.abs(n-i)<=len){
                //차이가 더 적거나 같을때
                if(Math.abs(n-i)==len) answer = Math.min(i,answer);
                else answer = i;
                len = Math.abs(n-i);
            }
        }
        return answer;
    }
}