/* n명이 각 입국심사대에서 심사를 받는다.
가장 앞에 서있는 사람은 빈 심사대로 가서 심사를 받는다.
더 빨리 끝나는 심사대가 있으면 기다렸다가 거기서 받을수도 있음.
모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고싶다. 이때 걸리는 시간을 구해라.

[풀이]
입력이 10억으로 개크니까 => 이분탐색
최대로 걸리는 시간은... MAX(times) x n
동시에 진행되니까 result = MAX(7x, 10y)가 되고, x+y=n 이겠지

1. 일단 min=0, max= MAX(times)xn 로 세팅하고
2. 이분탐색으로 mid를 선택해가며 해당 초 안에 입국심사가 가능한지 계산한다.
    - 최적값이기 위해서는 => 예를들어 mid=20초라면 => 7*2 + 10*2 => 4명까지만 가능한거임 적으니까 늘려
                       => 예를들어 mid=28초라면 => 7*4 + 10*2 => 6명!!                       
3. 이거 그거다 특정값 이상이 시작되는 위치를 찾는거,,, upper탐색

*/
import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long mid=0;
        long left=0, right=(long)times[times.length-1]*n;
        
        while(left < right) {
            mid = (left+right)/2;
            
            if(n <= validPerson(mid, times)) right = mid;
            else left = mid+1;
        }
        
        return left;
    }
    
    //time안에 처리할 수 있는 인원수를 리턴하는 함수
    public long validPerson(long time, int[] times) {
        long person = 0;
        for(int i : times) person += time/i;
        return person;
    }
}