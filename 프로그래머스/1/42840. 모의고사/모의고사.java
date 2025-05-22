//수포자들이 문제를 찍는다.정답이 주어질때 가장 많은 문제를 맞힌 사람을 리턴하라.
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] arr1 = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{2,1,2,3,2,4,2,5};
        int[] arr3 = new int[]{3,3,1,1,2,2,4,4,5,5};
        
        int[] cntArr = new int[3];
        for(int i=0; i<answers.length; i++) {
            if(arr1[i%5] == answers[i]) cntArr[0]++;
            if(arr2[i%8] == answers[i]) cntArr[1]++;
            if(arr3[i%10] == answers[i]) cntArr[2]++;    
        }
        
        int max = Math.max(cntArr[0],Math.max(cntArr[1],cntArr[2]));
        int[] answer = new int[3];
        int cnt=0;
        for(int i=0; i<3; i++) {
            if(cntArr[i] == max) answer[cnt++] = i+1;
        }
        
        int[] result = Arrays.copyOf(answer, cnt);
        
        
        return result;
    }
}