import java.util.*;
class Solution {
    public int solution(int n) {
        int[] arr = new int[n+1];
        for(int i=1; i<n+1; i++){
            //이전숫자+1이 3포함X, 3배수X 여야함
            int tmp = arr[i-1]+1;
        	while(tmp%3 == 0 || Integer.toString(tmp).contains("3")) {
        		tmp += 1;
        	}
            arr[i] = tmp;
        }
        int answer = arr[n];
        return answer;
    }
}