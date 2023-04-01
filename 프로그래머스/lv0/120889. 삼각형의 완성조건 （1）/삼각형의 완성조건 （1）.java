import java.util.*;
class Solution {
    public int solution(int[] sides) {
        int index=0, longs=0, sum=0, answer=2;
        for(int i=0; i<3; i++) {
        	if(sides[i] > longs) {
        		index = i;
        		longs = sides[i];
        	}
        }
        
        for(int i=0; i<3; i++){
            if(i != index) {
                sum += sides[i];
            }
        }
        
        if(longs < sum) answer = 1;
        
        return answer;
    }
}