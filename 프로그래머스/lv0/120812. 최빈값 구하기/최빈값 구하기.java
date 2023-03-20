import java.util.*;
class Solution {
    public int solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int a : array){
            if(map.containsKey(a)){
                map.put(a,map.get(a)+1);
            }else{
                map.put(a,1);
            }
        }
        
        int max=0;
        int answer=0;
        for(int k : map.values()){
        	max = Math.max(max,k);
        }
        
        int cnt = 1;
        for(int k : map.keySet()) {
        	if(map.get(k)==max) {
        		answer = k;
                cnt++;
        	}
        }
        if(cnt != 2) answer=-1;
        return answer;
    }
    
    
    
}