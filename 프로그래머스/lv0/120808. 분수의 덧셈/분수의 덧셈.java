import java.util.*;
class Solution {
	// n의 약수를 구하는 메소드
	public HashSet<Integer> solv2(int n){
		HashSet<Integer> answer = new HashSet<>();
        for(int i=1; i<=n; i++){
            int tmp = 0;
            int j=1;
            while(tmp < n){
                tmp = i*j;
                if(tmp==n){
                	answer.add(i);
                	answer.add(j);
                    break;
                }
                j++;
            }
        }
		return answer;
	}
	
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int n = numer1*denom2+numer2*denom1;
        int d = denom1*denom2;
        
        // 각 공약수 구하기
        HashSet<Integer> nSet = solv2(n);
        HashSet<Integer> dSet = solv2(d);
        
        //최대공약수 찾기
        int max = 1;
        for(int i : nSet) {
        	for(int j : dSet) {
        		if( i==j ) max = Math.max(max, j);
        	}
        }

        //최대공약수로 약분하기
        n /= max;
        d /= max;
        
        int[] answer = {n,d};
        return answer;
    }
}