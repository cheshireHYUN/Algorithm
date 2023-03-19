import java.util.*;
class Solution {
	
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int n = numer1*denom2 + numer2*denom1;
        int d = denom1*denom2;
        
        //최대공약수를 찾는법) 더 작은수를 1부터 나눠보면서 더 큰수에서도 나눠지는 수를 찾으면 됨
        int min = Math.min(n,d);
        int max = Math.max(n,d);
        int common = 1;
        for(int i=1; i<=min; i++){
            if(min%i==0 && max%i==0){
                common = i;
            }
        }
        
        //약분하고 리턴
        int[] answer = {n/common,d/common};
        return answer;
    }
}